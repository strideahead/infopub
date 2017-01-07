package com.lynuc.action;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.lynuc.bean.BaseAction;
import com.lynuc.bean.Mcc;
import com.lynuc.bean.ProductCard;
import com.lynuc.bean.SysLog;
import com.lynuc.bean.User;
import com.lynuc.dao.MccListDao;
import com.lynuc.def.Constant;
import com.lynuc.implDao.AddLogRecordActionImpl;
import com.lynuc.implDao.JdbcSqlMccDaoImpl;
import com.lynuc.util.JdbcUtil;
import com.lynuc.util.JdbcUtilJstore;
import com.lynuc.util.ParseConfiguration;
public class AddControlCardItemAction extends BaseAction implements Serializable{
	private static final long serialVersionUID = 8648979651404103302L;

	//从数据库获取添加控制器页面所需要的数据
	//此页面可能从添加控制器跳转来，也可能从编辑控制器跳转过来，对应页面上添加控制和编辑控制器
	public String execute(){
		Connection conn_mcc;
		try {
			conn_mcc = JdbcUtil.getConnection();
			String controller_no=request.getParameter("controller_no");
			String is_mobile=request.getParameter("mobile");
			List<Map<String,String>> contrl_verMapList=getControllerList(controller_no);
			
			List<Map<String,String>> contrl_noList = new ArrayList<Map<String,String>>(); 
			List<String> ctrl_noInMccList=new ArrayList<>();
			getCtrlnoInProductNotInMcc(conn_mcc,contrl_noList,ctrl_noInMccList);
			
			Map<String,String> mapdefault_custname=new HashMap<String,String>();	
			Map<String,String> mapdefault_office=new HashMap<String,String>();	
			getCustSaleCompanyList(conn_mcc,mapdefault_custname,mapdefault_office);
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar calendar=Calendar.getInstance();
	        Date date=calendar.getTime();
	        String default_setup_date=sdf.format(date);
	        
	        //如果从编辑控制器链接跳转过来则，获取此控制器的详细信息
			if(controller_no!=null){
				Mcc m=new Mcc();
				PreparedStatement pstmt=conn_mcc.prepareStatement("{call dbo.mccsel_byctrlno(?)}");
				pstmt.setString(1, controller_no);
				ResultSet rs=pstmt.executeQuery();
				
				if(rs.next()){
					try {
						m.getEntry(rs);
						Map<String,Object>  mccMap=new HashMap<String,Object>();
						//控制器信息转换成属性-值键值对
						m.convertMccToMap(mccMap);
						
						Map<String,String> contrl_verMap=new HashMap<String, String>();
						contrl_verMap.put("key", m.getController_ver());
						contrl_verMap.put("value", m.getController_ver());
						contrl_verMapList.add(contrl_verMap);
						request.setAttribute("mccMap", mccMap);
						request.setAttribute("defaultController_ver", m.getController_ver());
						
						Map<String,String> contrl_noMap=new HashMap<String, String>();
						contrl_noMap.put("controller_no", m.getController_no());
						contrl_noMap.put("card_no", m.getCard_no());
						contrl_noList.add(contrl_noMap);
						
						mapdefault_office.remove("gguid");
						mapdefault_office.put("gguid", m.getSale_company_gguid());
						mapdefault_custname.remove("gguid");
						mapdefault_custname.put("gguid", m.getCust_company_gguid());
						request.setAttribute("ctrlnoexist", 1);
						if(null != m.getSetup_date()) default_setup_date=m.getSetup_date();
						pstmt.close();
						rs.close();
						
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			//传递到添加或编辑控制机界面的request数据
			request.setAttribute("contrl_verMapList", contrl_verMapList);
			//控制器类型对应表
			//request.setAttribute("contrl_verList", contrl_verList);
			//默认事务所下拉列表值为lynuc
			request.setAttribute("mapdefault_office", mapdefault_office);
			//默认客户名下拉列表值为lynuc
			request.setAttribute("mapdefault_custname", mapdefault_custname);
			request.setAttribute("ctrl_noInMccList", ctrl_noInMccList);
			//可以添加的controller_no列
			request.setAttribute("contrl_noList", contrl_noList);
			//默认安装日为今天
			request.setAttribute("setup_date", default_setup_date.split(" ")[0]);
			
			if(null == is_mobile){
				is_mobile="no";
			}
			System.out.println("is_mobile="+is_mobile);
			request.setAttribute("is_mobile", is_mobile);
			conn_mcc.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}
	/*
	 * 添加界面，点击'确定并申请密码'、'确定‘后，对form的不同处理
	 * 编辑界面，点击'确定‘、’删除'后，对form的不同处理
	 */
	@SuppressWarnings("unchecked")
	public String submitAddtion() throws Exception
	{	
		Connection conn=JdbcUtil.getConnection(); // 获取数据库链接
		PreparedStatement pstmt=null;
		PreparedStatement pstmt2=null;
		ResultSet rs=null;
		/*获取到底点击哪个按钮后跳转过来的
		 * which_btn_click属性是界面的hidden值传递过来的，具体参照addControlCardItem.jsp
		 */
		String is_mobile=request.getParameter("is_mobile");
		System.out.println("is_mobile+===submitAddtion"+is_mobile);
		String which_button_click=request.getParameter("which_btn_click");
		String controller_no=request.getParameter("hiddentxt_controller_no");
		SysLog sl=new SysLog();
		StringBuilder log_memo=new StringBuilder("");
		//点击 '删除‘ 按钮
		if(which_button_click.equals("4")){
			//开启事务
			conn.setAutoCommit(false);
			/*
			 * 删除mcc表中controller_no，删除mcc_password中所有等于controller_no的记录
			 */
			StringBuilder sql=new StringBuilder("delete from mcc_password where mcc_password.controller_no=?");
			pstmt=conn.prepareStatement(sql.toString());
			pstmt.setString(1, controller_no);
			pstmt.executeUpdate();		 
			StringBuilder sqlMcc=new StringBuilder("delete from mcc where mcc.controller_no=?");
			pstmt2=conn.prepareStatement(sqlMcc.toString());
			pstmt2.setString(1, controller_no);
			pstmt2.executeUpdate();
			
			conn.commit();
	        conn.setAutoCommit(true);
	        
			log_memo.append("删除");
			
			if("yes".equals(is_mobile)){
				return "submitmobile";
			}

			return "submit";
		}
		//获取页面Post传递过来的参数
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		String controller_ver=request.getParameter("ddl_controller_ver");
		String machine_no=request.getParameter("txt_machine_no");
		String total_type=request.getParameter("txt_total_type");
		String machine_type=request.getParameter("txt_machine_type");
		String card_no=request.getParameter("txt_card_no");
		String password_ver=request.getParameter("txt_password_ver");
		String is_pay=request.getParameter("ddl_is_pay");
		String setup_date=request.getParameter("dp_setup_date");
		String sale_company_gguid=request.getParameter("ddl_office");
		String cust_company_gguid=request.getParameter("ddl_cust");
		java.sql.Statement stateCompanyid=conn.createStatement();
		String sqlcompanyid="select company_id from company where gguid='"+cust_company_gguid+"'";
		ResultSet rscompanyid=stateCompanyid.executeQuery(sqlcompanyid);
		int companyid=0;
		//若传递过来的客户名的company_id 则修正module_set值
		if(rscompanyid.next()){
			if(null != rscompanyid.getObject("company_id")){
				companyid=rscompanyid.getInt("company_id");
			}
		}
		String office_name=request.getParameter("hidden_office_name");
		String cust_name=request.getParameter("hidden_customer_name");
		String ver=request.getParameter("txt_ver");
		String hard_ver=request.getParameter("txt_hard_ver");
		String mac_option=request.getParameter("txt_mac_option");
		String lynuc_option=request.getParameter("txt_lynuc_option");
		String memo=request.getParameter("ftxt_memo");
		String input_time_str=request.getParameter("txt_input_time");
		
		/*
		 * 根据要求，个别模块作修正
		 */
		int mudule[]=new int[Constant.module_set.length];
		for(int i=0;i<Constant.module_set.length;i++){
			String name_str="module"+i;
			String mudule_selt=request.getParameter(name_str);	
			
			if(mudule_selt==null){
				if(i==0 || i==1 || i==2 || i==3 || i==5 || i==8){
					mudule[i]=1;
				}else{
					mudule[i]=0;
				}
			}
			else if(mudule_selt.equals("on")) {
				if( i==0 || i==1 || i==2 || i==3 || i==5 || i==8){
					mudule[i]=0;
				}else{
					mudule[i]=1;
				}
			}
		}
		int module_set=0;
		for(int i=0;i<Constant.module_set.length;i++){
			if(mudule[i]!=0) module_set+=1<<i;
		}
		if(companyid != 0){
			module_set = (companyid << 17) | module_set;
		}
		
		//登录人和登录时间分别为用户和当前时间
		User user=(User) sessionMap.get("user"); 
		int input_user_id=user.getId();
		String input_time="";
		if(input_time_str==null || "".equals(input_time_str)){
			Calendar calendar=Calendar.getInstance();
	        Date date=calendar.getTime();
	        input_time=sdf.format(date);
		}else{
			input_time=input_time_str;
		}
		
		//补充添加控制器的信息，插入到数据库
		Mcc m=new Mcc();
		ProductCard pc=new ProductCard();
		Connection connjstore=JdbcUtilJstore.getConnection();
		String card_no_str=request.getParameter("ddl_controller_no");
		if(which_button_click.equals("1") || which_button_click.equals("0")){
			String sqlProduct_card="select * from product_card where card_no =?";
			pstmt=connjstore.prepareStatement(sqlProduct_card);
			pstmt.setString(1, card_no_str);
			rs=pstmt.executeQuery();
			if(rs.next()){
				pc.getEntry(rs);
			}
			UUID uuid=UUID.randomUUID();
			String gguid=uuid.toString().toLowerCase();
			m.setGguid(gguid);
			
			m.setMachine_no(machine_no);
			m.setMachine_type(machine_type);
			m.setController_no(pc.getController_no());
			m.setCard_no(pc.getCard_no());
			m.setCard_content(pc.getCard_content());
			m.setIpc_sn(pc.getIpc_sn());
			m.setIs_old(pc.getIs_old());
			m.setIs_pay(is_pay);
			m.setOffice(office_name);
			m.setCust_name(cust_name);
			m.setCust_duty(null);
			m.setCust_tel(null);
			m.setMac_no(null);
			m.setVer(ver);
			m.setSetup_date(setup_date);	
			m.setHard_ver(hard_ver);
			m.setMemo(memo);
			m.setModule_set(module_set);
			m.setFile1path(null);
			m.setFile1desc(null);
			m.setFile2path(null);
			m.setFile2desc(null);
			m.setFile3path(null);
			m.setFile3desc(null);
			m.setSale_company_gguid(sale_company_gguid);
			m.setCust_company_gguid(cust_company_gguid);
			m.setTotal_type(total_type);
			m.setMac_option(mac_option);
			m.setLynuc_option(lynuc_option);
			m.setPassword_ver(password_ver);
			m.setController_ver(controller_ver);
			m.setMachine_dat(null);
			m.setEzdoctor_serial(null);
			m.setInput_user_id(input_user_id);
			m.setInput_time(input_time);
			MccListDao mccListDao=new JdbcSqlMccDaoImpl();
			boolean addcontroller_no=mccListDao.insertMcc_item(m);
			//如果失败则返回到主页时给出提示信息
			if(!addcontroller_no){
				sessionMap.put("fail", "true");
				return "fail";
			}
			log_memo.append("添加");
			//确定并申请密码，则跳转到申请密码页面
			if(which_button_click.equals("0")){
				sessionMap.put("controller_no_applypass", controller_no);
				return "submitAnd";
			}
		}
		//编辑页面，点击确定按钮
		if(which_button_click.equals("3")){
			String sqlProduct_card="select * from mcc where controller_no =?";
			pstmt=conn.prepareStatement(sqlProduct_card);
			pstmt.setString(1, card_no_str);
			rs=pstmt.executeQuery();
			if(rs.next()){
				String mcc_gguid=rs.getString("gguid");
				m.setMachine_no(machine_no);
				m.setMachine_type(machine_type);
				m.setIs_pay(is_pay);
				m.setOffice(office_name);
				m.setCust_name(cust_name);
				m.setVer(ver);
				m.setHard_ver(hard_ver);
				m.setLynuc_option(lynuc_option);
				m.setMac_option(mac_option);
				m.setMemo(memo);
				m.setModule_set(module_set);
				m.setSale_company_gguid(sale_company_gguid);
				m.setCust_company_gguid(cust_company_gguid);
				m.setTotal_type(total_type);
				m.setMac_option(mac_option);
				m.setLynuc_option(lynuc_option);
				m.setController_ver(controller_ver);	
				JdbcSqlMccDaoImpl jsmdi=new JdbcSqlMccDaoImpl();
				jsmdi.updateMyAddedMcc(m, mcc_gguid);
				log_memo.append("编辑");
			}
		}
		log_memo.append("机床/控制器/控制卡("+machine_no+" - "+controller_no+" - "+card_no+")");
		sl.setLog_memo(log_memo.toString());
		sl.setLog_type("6");
		AddLogRecordActionImpl logins=new AddLogRecordActionImpl();
		logins.insertSysLogToDB(sl);
		if("yes".equals(is_mobile)){
			return "submitmobile";
		}else{
			return "submit";
		}
		
	}
	private List<Map<String,String>> getControllerList(String Controller_no)
	{
		//控制器类型对应表，存在properties文件中
		ParseConfiguration eg=new ParseConfiguration("/controlnoList.properties");
		eg.parseProperties("key");
		List<String> contrl_verList=eg.getStrings();
		List<Map<String,String>> contrl_verMapList=new ArrayList<Map<String,String>>(); ;
		//List转换为Map，作为下拉列表的option
		for(int i=0;i<contrl_verList.size();i++){
			Map<String,String> contrl_verMap=new HashMap<String, String>();
			contrl_verMap.put("key", contrl_verList.get(i));
			contrl_verMap.put("value", contrl_verList.get(i));
			contrl_verMapList.add(contrl_verMap);
		}
		return contrl_verMapList;
	}
	//控制器号：下拉列表。从jstore库中挑选已入库(product_card)但没有加入到infopub.mcc中的所有控制器。
	private void getCtrlnoInProductNotInMcc(Connection conn_mcc,List<Map<String,String>> contrl_noList,List<String> ctrl_noInMccList)
	{
		try {
			Connection conn_prodcard=JdbcUtilJstore.getConnection();
			
			Statement stmt=conn_mcc.createStatement();	
			ResultSet rs=stmt.executeQuery("{call dbo.mcc_seldistinctCtrlno}");	
			while(rs.next()){
				ctrl_noInMccList.add(rs.getString("controller_no"));
			}
			
			stmt=conn_prodcard.createStatement();	
			rs=stmt.executeQuery("{call dbo.product_selCardnoCtrlno}");
			//由于product_card和mcc存在于不同的数据库中，故先获取两个列表，再筛选
			while(rs.next()){
				String ctrl_no=rs.getString("controller_no");
				//若控制器已经存在于mcc中则，不添加到下拉列表
				if(ctrl_noInMccList.contains(ctrl_no)) continue;
				Map<String,String> map=new HashMap<String,String>();
				map.put("controller_no", ctrl_no);
				map.put("card_no", rs.getString("card_no"));
				contrl_noList.add(map);
			}
			conn_prodcard.close();
			stmt.close();
			rs.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	/*
	 * 获取客户下拉别表，即company表中所有的公司信息
	 * 获取事务所下拉列表
	 */
	private void getCustSaleCompanyList(Connection conn,Map<String,String> mapdefault_custname,Map<String,String> mapdefault_office)
	{
		try {
			String sqlCustnameList="select gguid, company_name from company order by company_name";
			Statement stmt=conn.createStatement();
			ResultSet rs = stmt.executeQuery(sqlCustnameList);
			List<Map<String,String>> custnameList = new ArrayList<Map<String,String>>(); 
			
			while(rs.next()){
				Map<String,String> map=new HashMap<String,String>();
				map.put("gguid", rs.getString("gguid"));
				map.put("company_name", rs.getString("company_name"));
				if(rs.getString("company_name").equals("Lynuc")){
					mapdefault_office.put("gguid", rs.getString("gguid"));
				}
				custnameList.add(map);
			}
			//获取事务所列表。界面上的事务所一栏中的公司type必须大于等于2
			String sqlOfficeList="select gguid, company_name from company where company_type>=2 order by company_name";
			rs=stmt.executeQuery(sqlOfficeList);
			List<Map<String,String>> officeList = new ArrayList<Map<String,String>>(); 
				
			while(rs.next()){
				Map<String,String> map=new HashMap<String,String>();
				map.put("gguid", rs.getString("gguid"));
				map.put("company_name", rs.getString("company_name"));
				if(rs.getString("company_name").equals("Lynuc")){
					mapdefault_custname.put("gguid", rs.getString("gguid"));
				}
				officeList.add(map);
			}
			//客户下拉列表
			request.setAttribute("custnameList", custnameList);
			//事务所下拉列表
			request.setAttribute("officeList", officeList);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
