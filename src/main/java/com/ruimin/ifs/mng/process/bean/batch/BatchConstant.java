
package com.ruimin.ifs.mng.process.bean.batch;

public class BatchConstant {

	public static final String BATCH_PROPERTY_FILE = "batch.properties";
	public static final String BATCH_PROPERTY_LOADFILE_SHELL = "LoadFileShell";
	public static final String BATCH_PROPERTY_EXPORT_TABLE_SHELL = "ExportTableShell";
	public static final String BATCH_PROPERTY_SHELL_PATH = "ShellPath";
	public static final String BATCH_PROPERTY_PERSONAL_CREDIT_PATH = "PersonalCreditPath";
	/**
	 * 备份
	 */
	public static final String BATCH_PROPERTY_BACKUPFILE_SHELL = "BackupFileShell";
	/**
	 * 清表
	 */
	public static final String BATCH_PROPERTY_DELETE_TABLE = "DeleteTable";
	/**
	 * RUNSTATS
	 */
	public static final String BATCH_PROPERTY_RUNSTATS = "RunStats";
	/**
	 * 批量导入更新文件存放路径
	 */
	public static final String BATCH_PROPERTY_LOADFILE_PATH = "LoadPath";
	public static final String BATCH_PROPERTY_LOAD_CHECK_FILE = "LoadCheckFile";
	public static final String BATCH_PROPERTY_IBS_CHECK_FILE = "IbsCheckFile";
	public static final String BATCH_PROPERTY_IBP_CHECK_FILE = "IbpCheckFile";
	public static final String BATCH_PROPERTY_CMIS_CHECK_FILE = "CmisCheckFile";
	public static final String BATCH_PROPERTY_RIRS_CHECK_FILE = "RirsCheckFile";
	public static final String BATCH_PROPERTY_IBS_CHECK_HOUR = "ibsHour";
	public static final String BATCH_PROPERTY_IBS_CHECK_MINU = "ibsMinu";
	public static final String BATCH_PROPERTY_CMIS_CHECK_HOUR = "cmisHour";
	public static final String BATCH_PROPERTY_CMIS_CHECK_MINU = "cmisMinu";
	// 批量导入分行更新文件存放路径 2006/02/27
	public static final String BATCH_PROPERTY_BRH_LOADFILE_PATH = "BrhLoadPath";
	// 银信通-贷款到期提醒提前天数 2006/02/27
	public static final String BATCH_PROPERTY_SMS_ALTER_DAYS = "SmsRepayPlanWarnDays";
	/**
	 * 批量倒出表数据文件存放路径
	 */
	public static final String BATCH_PROPERTY_EXPORT_TABLE_PATH = "ExportTablePath";
	/*
	 * 报表运行周期,目前用于质量迁徙表
	 */
	public static final String PROC_PARAM_MONTH = "MONTH";
	public static final String PROC_PARAM_SEASON = "SEASON";
	public static final String PROC_PARAM_SEMIYEAR = "SEMIYEAR";
	public static final String PROC_PARAM_YEAR = "YEAR";
	/**
	 * add by weikun wang 2008.7.22
	 */
	public final static String POINT = ".";

	public static final String PDF_FILE_POSTFIX = "pdf";

	public static final String EXCEL_FILE_POSTFIX = "xls";

	public static final String HTML_FILE_POSTFIX = "html";

	public static final String XMLJ_FILE_POSTFIX = "xml";

	public static final String RTF_FILE_POSTFIX = "rtf";

	public static final String CSV_FILE_POSTFIX = "csv";

	/**
	 * 主机下来的批量文件存放路径
	 */
	public static final String BATCH_HOST_SOURCE_FILE_PATH = "HostSourceFilePath";
	public static final String BATCH_IBP_SOURCE_FILE_PATH = "IBPUpFilePath";
	/**
	 * 负责主机下来的批量文件进行解析的xml文件 add by weikun wang 2008.8.11
	 */
	public static final String BATCH_HOST_XML_FILE_PATH = "CoreFilesXmlPath";
	/**
	 * 批量配置文件属性：批量日志存放路径
	 */
	public static final String BATCH_LOG_PATH = "batchLogPath";

	/**
	 * 批量配置文件属性：批量生成报表文件存放路径
	 */
	public static final String BATCH_PROPERTY_REPORT_GEN_PATH = "batchReportGenPath";
	/**
	 * # 批量报表模版 *.jasper文件路径
	 */
	public static final String BATCH_PROPERTY_JASPER_FILE_PATH = "batchReportJasperFilePath";

	/**
	 * 数据库类型。
	 */
	public static final String DataBase_type = "DataBase_type";

	/**
	 * 批量配置文件属性：批量报表文件打包路径
	 */
	public static final String BATCH_PROPERTY_REPORT_TAR_PATH = "batchReportTarPath";

	// 释放所有被锁住的资源-BoCom00018160
	public static final String BATCH_PROPERTY_FORCE_APPLICATION_SHELL = "ForceApplicationShell";

	/**
	 * 数据库连接用
	 */
	public static final String BATCH_PROPERTY_DB_DRIVER = "DataBase_Driver";
	public static final String BATCH_PROPERTY_DB_URL = "DataBase_URL";
	public static final String BATCH_PROPERTY_SHELL_DB_URL = "DataBase_SHELL_URL";
	public static final String BATCH_PROPERTY_DB_USER = "DataBase_UserID";
	public static final String BATCH_PROPERTY_DB_SCHEMA = "DataBase_Schema";
	public static final String BATCH_PROPERTY_DB_PASSWORD = "DataBase_Password";

	/**
	 * 应用服务器FTP信息 add by shen_antonio 20081115
	 */
	public static final String BATCH_PROPERTY_APP_SERVER_IP = "APP_SERVER_IP";
	public static final String BATCH_PROPERTY_APP_SERVER_FTP_PORT = "APP_SERVER_FTP_PORT";
	public static final String BATCH_PROPERTY_APP_SERVER_FTP_USERNAME = "APP_SERVER_FTP_USERNAME";
	public static final String BATCH_PROPERTY_APP_SERVER_FTP_PWD = "APP_SERVER_FTP_PWD";
	public static final String BATCH_PROPERTY_APP_SERVER_FTP_REPORT_PATH = "APP_SERVER_FTP_REPORT_PATH";
	/** . */

	/**
	 * 通知应用服务器解压缩报表压缩包URL add by shen_antonio 20081115
	 */
	public static final String BATCH_PROPERTY_ReportCallAppServerURL = "ReportCallAppServerURL";
	public static final String BATCH_PROPERTY_APPSERVER_SOCKET_TIMEOUT = "AppServerSocketTimeOut";
	public static final String BATCH_PROPERTY_APPSERVER_CONNECT_TIMEOUT = "AppServerConnectTimeOut";
	/** . */

	/**
	 * 备份数据库用户
	 */
	public static final String BATCH_PROPERTY_BACKUP_USER = "Backup_UserID";
	public static final String BATCH_PROPERTY_BACKUP_PASSWORD = "Backup_Password";
	public static final String BATCH_PROPERTY_BPEDBBACKUP_USER = "BPEDBBackup_UserID";
	public static final String BATCH_PROPERTY_BPEDBBACKUP_PASSWORD = "BPEDBBackup_Password";
	public static final String BATCH_PROPERTY_BACKUP_PATH = "Backup_Path";
	/**
	 * 批配置文件
	 */
	public static final String BATCH_FILE_XML = "batch_file.xml";
	public static final String BATCH_FILE_XML_ROOT = "BatchCoreFiles";
	public static final String BATCH_FILE_XML_FILES_FILED = "files";
	public static final String BATCH_FILE_XML_FILE_ITEM = "item";
	public static final String BATCH_FILE_XML_FILE_VALUE = "file";
	public static final String BATCH_FILE_XML_TABLE_VALUE = "table";

	/**
	 * 转换批量文件配置文件,XML标签
	 */
	public static final String BATCH_CONVERT_FILE_XML = "convert_file.xml";
	/**
	 * 根目录标签
	 */
	public static final String BATCH_CONVERT_FILE_XML_ROOT = "convertFiles";
	/**
	 * 批量文件标签
	 */
	public static final String BATCH_CONVERT_FILE_XML_FILE = "file";
	/**
	 * 文件头名称标签
	 */
	public static final String BATCH_CONVERT_FILE_XML_FILE_HEAD_NAME = "name";
	/**
	 * 文件头栏位标签
	 */
	public static final String BATCH_CONVERT_FILE_XML_FILE_HEAD_FIELD = "field";
	/**
	 * 文件头栏位名称标签
	 */
	public static final String BATCH_CONVERT_FILE_XML_FILE_HEAD_FIELD_NAME = "name";
	/**
	 * 文件头是否需要校验
	 */
	public static final String BATCH_CONVERT_FILE_XML_FILE_HEAD_NEED_VERIFY = "needverify";
	/**
	 * 文件头栏位开始列位置
	 */
	public static final String BATCH_CONVERT_FILE_XML_FILE_HEAD_FIELD_BEGIN_POSITION = "begin";
	/**
	 * 文件头栏位终止列位置
	 */
	public static final String BATCH_CONVERT_FILE_XML_FILE_HEAD_FIELD_END_POSITION = "end";
	/**
	 * 文件名标签
	 */
	public static final String BATCH_CONVERT_FILE_XML_FILE_NAME = "name";
	/**
	 * 文件所使用文件头标签
	 */
	public static final String BATCH_CONVERT_FILE_XML_FILE_HEAD = "fileHead";

	/**
	 * 文件所使用文件体文件头标签
	 */
	public static final String BATCH_CONVERT_FILE_XML_FILE_BODY_HEAD = "fileBodyHead";
	/**
	 * 转换数据源文件
	 */
	public static final String BATCH_CONVERT_FILE_XML_FILE_SOURCEFILE = "sourceFile";
	/**
	 * 数据源文件前缀
	 */
	public static final String BATCH_CONVERT_FILE_XML_FILE_SOURCEFILE_PREFIX = "sourceFilePrefix";
	/**
	 * 最小保证字段数目
	 */
	public static final String BATCH_CONVERT_FILE_XML_FILE_MIN_FIELD_NUMS = "minFieldNums";
	/**
	 * 目标存放文件
	 */
	public static final String BATCH_CONVERT_FILE_XML_FILE_TARGETFILE = "targetFile";
	/**
	 * 目标表
	 */
	public static final String BATCH_CONVERT_FILE_XML_FILE_TARGETTABLE = "targetTable";
	/**
	 * 是否删除临时表
	 */
	public static final String BATCH_CONVERT_FILE_XML_FILE_DELETE_TEMP = "deleteTempTable";
	/**
	 * 删除CONDITION
	 */
	public static final String BATCH_CONVERT_FILE_XML_FILE_CONDITION = "hasDeleteCondition";
	/**
	 * 文件TXDATE
	 */
	public static final String BATCH_CONVERT_FILE_XML_FILE_TXDATE = "hasFileTxdate";

	/**
	 * 是否EBCD码
	 */
	public static final String BATCH_CONVERT_FILE_XML_FILE_EBCD = "isEBCD";

	/**
	 * 可忽律错误行数
	 */
	public static final String BATCH_CONVERT_FILE_XML_FILE_SKIPERROWNUMS = "skipErrRowNums";

	/**
	 * 字段分隔符
	 */
	public static final String BATCH_CONVERT_FILE_XML_FILE_SEPARATOR = "separator";
	/**
	 * 字段标签
	 */
	public static final String BATCH_CONVERT_FILE_XML_FIELD = "field";
	/**
	 * 属性标签
	 */
	public static final String BATCH_CONVERT_FILE_XML_ATTRIBUTE = "attribute";
	/**
	 * 是否解析字段标签
	 */
	public static final String BATCH_CONVERT_FILE_XML_NEED = "need";
	/**
	 * 数据库列名字段
	 */
	public static final String BATCH_CONVERT_FILE_XML_COLUMN = "column";

	/**
	 * 实际人工需调整调整精度 add by weikun.wang 2009-2-26
	 */
	public static final String BATCH_CONVERT_FILE_XML_REAL_PRECISION = "realPrecision";
	/**
	 * 合法性检查标签
	 */
	public static final String BATCH_CONVERT_FILE_XML_LEGALCHECK = "legalcheck";
	/**
	 * char类型
	 */
	public static final String XML_CHAR = "CHAR";
	/**
	 * char类型
	 */
	public static final String XML_NUMCHAR = "NUMCHAR";
	/**
	 * dec类型
	 */
	public static final String XML_DEC = "DEC";
	/**
	 * smallint类型
	 */
	public static final String XML_SMALLINT = "SMALLINT";
	/**
	 * NUMASC类型
	 */
	public static final String XML_NUMASC = "NUMASC";
	/**
	 * CHARASC类型
	 */
	public static final String XML_CHARASC = "CHARASC";
	/**
	 * 冗余字段
	 */
	public static final String XML_UNUSE = "UNUSE";
	/**
	 * 删表一次提交笔数
	 */
	public static final int DELETE_COMMIT_CNT = 10000;
	/**
	 * 批量运行循环检查线程监视器时间间隔（毫秒）
	 */
	public static final int BATCH_LOOP_INTERVAL = 3000;
	/**
	 * 系统默认同时运行最大线程数
	 */
	public static final int DEFAULT_MAX_THREAD_NUM = 3;
	/**
	 * 总控线程名称
	 */
	public final static String GENERAL_CONTROL_THREAD_NAME = "GeneralControlThread";
	/**
	 * 系统默认数据库commit笔数
	 */
	public static final int DEFAULT_COMMIT_COUNT = 500;
	// 银信通批量提交commit笔数
	public static final int BANK_SMS_COMMIT_COUNT = 500;
	/**
	 * 全局数据表ID
	 */
	public static final int TABLE_GLOBAL_INFO_ID = 1;
	// TODO!!!!!!!! change back to 1

	/**
	 * 标志为已置
	 */
	public static final String FLAG_ON = "1";
	/**
	 * 标志为未置
	 */
	public static final String FLAG_OFF = "0";

	/**
	 * 利率率状态：已确认
	 */
	public static final String INTRATE_STATE_CONFIRMED = "0";
	/**
	 * 利率率状态：新增未确认
	 */
	public static final String INTRATE_STATE_NEW_UNCONFIRMED = "1";
	/**
	 * 利率率状态：删除未确认
	 */
	public static final String INTRATE_STATE_DELETE_UNCONFIRMED = "2";
	/**
	 * 批量运行模式：新运行
	 */
	public static final int BATCH_RUNNING_MODE_NEW = 0;
	/**
	 * 批量运行模式：重新运行
	 */
	public static final int BATCH_RUNNING_MODE_RESTART = 1;
	/**
	 * 线程运行状态：未运行
	 */
	public final static String THREAD_STATUS_OPEN = "O";
	/**
	 * 线程运行状态：运行中
	 */
	public final static String THREAD_STATUS_RUNNING = "R";
	/**
	 * 线程运行状态：已出错
	 */
	public final static String THREAD_STATUS_ERROR = "E";
	/**
	 * 线程运行状态：已完成
	 */
	public final static String THREAD_STATUS_FINISHED = "F";
	/**
	 * 线程运行状态: 严重错误线程未能清理现场
	 */
	public final static String THREAD_STATUS_UNKNOWE_ERROR = "UE";
	/**
	 * 线程运行出错状态：新出现错误
	 */
	public final static String BATCH_ERROR_TYPE_NEW = "N";
	/**
	 * 线程运行出错状态：已存在错误
	 */
	public final static String BATCH_ERROR_TYPE_EXISTS = "E";
	/**
	 * 线程运行出错状态：无错误
	 */
	public final static String BATCH_ERROR_TYPE_NO_ERROR = "O";

	/**
	 * 批量步骤多分行并行标志：启用多分行并行
	 */
	public final static String BRANCH_PARALLEL_BATCH_TRUE = "1";
	/**
	 * 批量步骤多分行并行标志：不启用多分行并行
	 */
	public final static String BRANCH_PARALLEL_BATCH_FALSE = "0";
	/**
	 * 临时监控标志：有效。 设为有效后该批量子步骤这次不运行
	 */
	public final static String TEMP_FLAG_TRUE = "1";
	/**
	 * 临时监控标志：无效。 设为无效后该标志不影响该批量子步骤执行
	 */
	public final static String TEMP_FLAG_FALSE = "0";
	/**
	 * 批量运行出错可忽略标志： 可忽略
	 */
	public static final String BATCH_ERROR_IGNORE_YES = "1";
	/**
	 * 批量运行出错可忽略标志： 不可忽略
	 */
	public static final String BATCH_ERROR_IGNORE_NO = "0";
	/**
	 * 机构级别：分行
	 */
	public static final String BRCLASS_BRANCH = "1";

	/**
	 * 批量子步骤运行时间：不跑
	 */
	public static final String SUB_STEP_RUN_TIME_NONE = "9N";
	/**
	 * 批量子步骤运行时间：每日
	 */
	public static final String SUB_STEP_RUN_TIME_DAILY = "90";
	/**
	 * 批量子步骤运行时间：每旬（固定在周五批量）
	 */
	public static final String SUB_STEP_RUN_TIME_EVERY_TEN_DAYS = "91";
	/**
	 * 批量子步骤运行时间：每月
	 */
	public static final String SUB_STEP_RUN_TIME_MONTHLY = "93";
	/**
	 * 批量子步骤运行时间：每季
	 */
	public static final String SUB_STEP_RUN_TIME_EVERY_SEASON = "94";
	/**
	 * 批量子步骤运行时间：每半年
	 */
	public static final String SUB_STEP_RUN_TIME_EVERY_HALF_YEAR = "95";
	/**
	 * 批量子步骤运行时间：每年
	 */
	public static final String SUB_STEP_RUN_TIME_YEARLY = "96";

	/**
	 * 系统设定常量：星期一
	 */
	public static final String WEEKDAY_MONDAY = "41";
	/**
	 * 系统设定常量：星期二
	 */
	public static final String WEEKDAY_TUESDAY = "42";
	/**
	 * 系统设定常量：星期三
	 */
	public static final String WEEKDAY_WEDNESDAY = "43";
	/**
	 * 系统设定常量：星期四
	 */
	public static final String WEEKDAY_THURSDAY = "44";
	/**
	 * 系统设定常量：星期五
	 */
	public static final String WEEKDAY_FRIDAY = "45";
	/**
	 * 系统设定常量：星期六
	 */
	public static final String WEEKDAY_SATURDAY = "46";
	/**
	 * 系统设定常量：星期日
	 */
	public static final String WEEKDAY_SUNDAY = "47";

	/**
	 * 报表生成类型:日报
	 */
	public static final String REPORT_TYPE_DAILY = "1";
	/**
	 * 报表生成类型:旬报
	 */
	public static final String REPORT_TYPE_TEN_DAYS = "2";
	/**
	 * 报表生成类型:月报
	 */
	public static final String REPORT_TYPE_MONTHLY = "3";
	/**
	 * 报表生成类型:年报
	 */
	public static final String REPORT_TYPE_YEARLY = "4";

	/**
	 * 操作员类型：信贷员
	 */
	public static final String TLR_TYPE_STAFFER = "2";
	/**
	 * 操作员类型：客户经理
	 */
	public static final String TLR_TYPE_MANAGER = "1";

	/**
	 * 逾期贷款细状态：非逾期
	 */
	public static final String OVD_LOAN_STAT_NA = "0";
	/**
	 * 逾期贷款细状态：0-6天逾期
	 */
	public static final String OVD_LOAN_STAT_0_TO_6_DAYS = "1";
	/**
	 * 逾期贷款细状态：7-13天逾期
	 */
	public static final String OVD_LOAN_STAT_7_TO_13_DAYS = "2";
	/**
	 * 逾期贷款细状态：14-29天逾期
	 */
	public static final String OVD_LOAN_STAT_14_TO_29_DAYS = "3";
	/**
	 * 逾期贷款细状态：30-59天逾期
	 */
	public static final String OVD_LOAN_STAT_30_TO_59_DAYS = "4";
	/**
	 * 逾期贷款细状态：60-89天逾期
	 */
	public static final String OVD_LOAN_STAT_60_TO_89_DAYS = "5";
	/**
	 * 逾期贷款细状态：90-179天逾期
	 */
	public static final String OVD_LOAN_STAT_90_TO_179_DAYS = "6";
	/**
	 * 逾期贷款细状态：180天-1年逾期
	 */
	public static final String OVD_LOAN_STAT_180_DAYS_TO_1_YEAR = "7";

	/**
	 * 贷款担保方式：1-质押（含保证金）
	 */
	public static final String GUARANT_TYPE_ZHIYA = "1";
	/**
	 * 贷款担保方式：2-抵押
	 */
	public static final String GUARANT_TYPE_DIYA = "2";
	/**
	 * 贷款担保方式：3-保证
	 */
	public static final String GUARANT_TYPE_GUARANTEE = "3";
	/**
	 * 贷款担保方式：4-信用/免担保
	 */
	public static final String GUARANT_TYPE_CREDIT = "4";
	/**
	 * 贷款担保方式：5-组合（含保证）
	 */
	public static final String GUARANT_TYPE_COMBINATION_GUARANTEE_INCLUDE = "5";
	/**
	 * 贷款担保方式：6-组合（不含保证）
	 */
	public static final String GUARANT_TYPE_COMBINATION_GUARANTEE_EXCLUDE = "6";
	/**
	 * 贷款担保方式：9-其他
	 */
	public static final String GUARANT_TYPE_OTHERS = "9";

	/**
	 * 利率时限：年利率
	 */
	public static final String INT_PERIOD_YEAR = "0";
	/**
	 * 利率时限：月利率
	 */
	public static final String INT_PERIOD_MONTH = "1";

	/**
	 * 利率时限：日利率
	 */
	public static final String INT_PERIOD_DAY = "2";

	/*
	 * 运行参数
	 */
	public static final String PROC_PARAM_HEAD = "HEAD";
	public static final String PROC_PARAM_BRANCH = "BRANCH";

	/**
	 * 下发数据
	 */
	public static final String BATCH_PROPERTY_DISPENSE_DATA = "DispenseData";
	public static final String BATCH_PROPERTY_DISPENSE_CMIS = "Exptocmis";

	/**
	 * 打包并且压缩数据
	 */
	public static final String BATCH_PROPERTY_DISPENSE_DATA_TAR_COMPRESS = "DispenseDataTarCompress";

	/**
	 * 报表打包压缩
	 */
	public static final String BATCH_PROPERTY_REPORT_TAR_COMPRESS = "ReportTarCompress";

	/**
	 * 下发数据路径
	 */
	public static final String BATCH_PROPERTY_DISPENSE_DATA_PATH = "DispenseDataPath";
	public static final String BATCH_PROPERTY_CMIS_DATA_PATH = "CmisDataPath";
	public static final String BATCH_PROPERTY_CMIS_BEFORE_DATA_PATH = "CmisBeforeDataPath";
	public static final String BATCH_PROPERTY_RIRS_DATA_PATH = "RirsDataPath";
	public static final String BATCH_PROPERTY_IBP_DATA_PATH = "IBPBeforeDataPath";

	/**
	 * 创建路径
	 */
	public static final String BATCH_PROPERTY_DISPENSE_CREATE_PATH = "DispenseCreatePath";

	/**
	 * 冲还贷结果状态
	 */
	public static final String AFRTN_STATUS_PART_ADV_SUCCESS = "0"; // 部分提前还款成功
	public static final String AFRTN_STATUS_ALL_ADV_SUCCESS = "1"; // 全部提前还款
	public static final String AFRTN_STATUS_OVD_SUCCESS = "2"; // 还逾期成功
	public static final String AFRTN_STATUS_CURR_SUCCESS = "3"; // 还当期成功
	public static final String AFRTN_STATUS_PART_CURR_SUCCESS = "4"; // 部分还当期成功
	public static final String AFRTN_STATUS_OVD_FAIL = "5"; // 还逾期失败
	public static final String AFRTN_STATUS_CURR_FAIL = "6"; // 还当期失败
	public static final String AFRTN_STATUS_ADV_FAIL = "7"; // 提前还款失败

	public static final String AFRTN_RTN_CODE_SUCCESS = "PL0000"; // 冲还贷结果为成功

	/*
	 * MQ参数
	 */
	public static final String BATCH_PROPERTY_MQ_QMANGER = "QueueManger";
	// 队列管理器
	public static final String BATCH_PROPERTY_MQ_QUEUE = "Queue"; // 队列名
	/*
	 * MQEnvironment参数
	 */
	public static final String BATCH_PROPERTY_MQ_HOSTNAME = "HostName"; // 主机名
	public static final String BATCH_PROPERTY_MQ_CHANNEL = "Channel"; // 通道名
	public static final String BATCH_PROPERTY_MQ_PORT = "Port"; // 端口号
	public static final String BATCH_PROPERTY_MQ_CCSID = "CCSID";
	/*
	 * Trace文件所在目录
	 */
	public static final String BATCH_PROPERTY_TRACEPATH = "TracePath";
	/*
	 * 无锡公积金数据文件所在目录
	 */
	public static final String BATCH_PROPERTY_IMPORTDATA_PATH = "ImportDataPath";
	/*
	 * 年终利率浮动主机文件所在目录
	 */
	public static final String BATCH_PROPERTY_FLTINTRATE_PATH = "HostFltintrateFilePath";

	/**
	 * 联机批量配置文件属性：联机批量报表文件路径
	 */
	public static final String OB_REPORT_PATH = "obReportPath";
	// 后台线程开始运行延迟时间(秒)
	public static final String ONLINE_BATCH_SCHEDULER_DELAY = "onlinebatch_scheduler_delay";
	// 后台线程运行间隔时间(秒)
	public static final String ONLINE_BATCH_SCHEDULER_PEROID = "onlinebatch_scheduler_period";
	// 队列长度
	public static final String ONLINE_BATCH_QUEUE_CAPACITY = "onlinebatch_queue_capacity";
	// 联机批量最大并发数
	public static final String ONLINE_BATCH_CONCURRENT_COUNT = "onlinebatch_concurrent_count";

	public static final String ONLINE_BATCH_LOG_FILE = "OnlineBatchLog";
	/**
	 * 现金回增上传数据路径
	 */
	public static final String BATCH_PROPERTY_UPLOAD_DATA_PATH = "UploadDataPath";

	// separator for category and property for HashMap
	public final static char C_SEP = 127;
	// prefix for category names
	public final static char C_PFIX = 64;

	public final static String POOL_CONFIG_FILE = "PoolConfig.xml";
	public final static String POOL_CONFIG_INSTANCE = "PoolConfigInstance";

	/**
	 * 使用旧的批量文件转换方式的文件列表 add by yjb
	 */
	public final static String OLD_CONVERT_FILE = "OldConvertFile";

	/**
	 * 核查状态
	 */
	// 未核查
	public final static String VF_STATUS_UNVERIFIED = "0";
	// 同意
	public final static String VF_STATUS_AGREE = "1";
	// 不同意
	public final static String VF_STATUS_DISAGREE = "2";
	// 退回
	public final static String VF_STATUS_WITHDRAW = "3";

	/**
	 * 批量作业是否允许自动运行前续作业
	 */
	// 自动运行
	public final static String BH_JOB_INFO_PRE_AUTORUN_ALLOW = "1";
	// 不自动运行
	public final static String BH_JOB_INFO_PRE_AUTORUN_DISALLOW = "0";

	/**
	 * 批量作业系统类型
	 */
	// 联机环境
	public final static String BH_JOB_INFO_SYSTEM_TYPE_ONLINE = "01";
	// 报表批量环境
	public final static String BH_JOB_INFO_SYSTEM_TYPE_BATCH = "02";

	/**
	 * 批量作业是否允许在联机状态下运行
	 */
	// 允许
	public final static String BH_JOB_INFO_RUNONLINE_ALLOW = "1";
	// 不允许
	public final static String BH_JOB_INFO_RUNONLINE_DISALLOW = "0";

	// 不存在前续作业标志
	public final static int BH_PRE_JOB_INFO_NOT_EXIST = 0;

	/**
	 * 导出导入报表服务器
	 */
	// 全表导入
	public static final String BHLoadTable_FULL = "BHLoadTableFull";
	// 全表导出
	public static final String BHLoadTable_EXPORT_FULL = "BHExportTableFull";
	// 增量导入
	public static final String BHLoadTable_DELTA = "BHLoadTableDelta";
	// 增量导出
	public static final String BHLoadTable_EXPORT_DELTA = "DispenseData";
	// 全量重入
	public static final String BHLoadTable_RESTART = "BHLoadTableRestart";
	// 增量重入
	public static final String BHLoadTable_RESTART_DELTA = "BHLoadTableRestart";
	/**
	 * 用于更新SEQCTL表
	 */
	public static final int VALUE_NO = 10;
	public static final String VALUE_INDEX = "00000000000000000000";

	// 批量文件根目录(包含上传及下载及报表)
	/* Added by Robin Suo On 20090812 */
	public static final String BATCH_PROPERTY_BATCHFILE_PATH = "batchFilePath";
	public static final String DIRECTION_UPLOAD = "upload";
	public static final String DIRECTION_DOWNLOAD = "download";
	public static final String FILE_TYPE_RPT = "rpt"; // 文件类型为0-报表
	public static final String FILE_TYPE_NET = "net"; // 文件类型为1-网银
	public static final String FILE_TYPE_CORE = "core"; // 文件类型为2-核心
	public static final String FILE_TYPE_ARMS = "arms"; // 文件类型为3-风管

	// ////////////20090816yuangx 补充,将SystemConstant
	// 里面的与业务系统无关的批量全局变量移植到BatchConstant中.SystemConstant满足完全只定义业务系统变量.

	/**
	 * RPT_BRCLASS 机构表中统计或报表机构级别
	 */
	public static final String RPT_BRCLASS_HEAD = "0"; // -总行
	public static final String RPT_BRCLASS_BRANCH = "1"; // -省分行
	public static final String RPT_BRCLASS_ZHI_BRANCH = "2"; // 直属行
	public static final String RPT_BRCLASS_XIA_BRANCH = "3"; // 辖属行
	public static final String RPT_BRCLASS_SUB_BRANCH_USEAS_BRANCH = "4"; // -省辖分行
	public static final String RPT_BRCLASS_SUBBRANCH = "5"; // -支行

	/**
	 * other_area_flag 异地行标志 0-非异地行；1-异地行本部；2-异地网点
	 */
	public static final String OTHER_AREA_NOT_LOCAL = "0"; // 0-非异地行
	public static final String OTHER_AREA_MNG_BRANCH_SELF = "1"; // 1-异地行本部
	public static final String OTHER_AREA_SUBBRANCH = "2"; // 2-异地网点

	public static final int VALUE_NO_BRCODE = 10; // 内部机构号

	/**
	 * 机构级别
	 */
	public static final String BRCODE_CLASS_HEAD = "0";
	public static final String BRCODE_CLASS_BRANCH = "1";
	public static final String BRCODE_CLASS_SUBBRANCH = "2";

	/**
	 * Mgr_flg 地区表中管理标志
	 */
	public static final String MGR_FLG_MNG_BRANCH_SELF = "0"; // -省分行本部
	public static final String MGR_FLG_SUB_BRANCH_NOT_LOCAL = "1"; // -异地支行
	public static final String MGR_FLG_SUB_BRANCH_USEAS_BRANCH = "2"; // -省辖行
	public static final String MGR_FLG_SUB_BRANCH_USEAS_BRANCH_CHILD = "3"; // -省辖行异地支行

	/**
	 * 字符串长度
	 */
	public static final int STRING_BUFFER_LEN_MID = 1024;
	public static final int STRING_BUFFER_LEN_MIN = 256;
	public static final int STRING_BUFFER_LEN_MAX = 4096;

	public static final String MERGEINFO_FLAG_VALID = "1"; // 有效
	public static final String MERGEINFO_FLAG_INVALIDATION = "0"; // 无效

	/**
	 * StringPattern用于解析xml文件时用
	 */
	public static final String SOURCE_FILE_PATTERN = "^.*\\{[^ ]+\\}.*$";//
	public static final String REPLACE_SOURCE_FILE_PATTERN = "\\{[^ ]+\\}";//

	/**
	 * 合同号(授信号)关联类型，和贷款性质对应
	 */
	public static final String CONTRACTMAP_TYPE_COMB = "02"; // 组合贷款
	public static final String CONTRACTMAP_TYPE_ADD = "03"; // 加按贷款
	public static final String CONTRACTMAP_TYPE_RELOAN = "04"; // 转按贷款
	public static final String CONTRACTMAP_TYPE_EXTEND = "05"; // 展期贷款
	public static final String CONTRACTMAP_TYPE_CREDIT = "06"; // 授信循环贷款
	public static final String CONTRACTMAP_TYPE_EDUAFTER = "07"; // 助学贷款离校期

	/**
	 * 贷款状态
	 */
	public static final String TRM_CLASS_INVALID = "A"; // 无效的合同(未发放的合同)
	public static final String TRM_CLASS_NORMAL = "0"; // 正常
	public static final String TRM_CLASS_OVERDUE = "1"; // 逾期
	public static final String TRM_CLASS_IDLE = "2"; // 呆滞
	public static final String TRM_CLASS_BAD = "3"; // 呆帐
	public static final String TRM_CLASS_LOSS = "4"; // 核销
	public static final String TRM_CLASS_CLOSE = "5"; // 结清

	/**
	 * 数据字典映射类型
	 */
	public static final int DATADIC_MAPTYPE_GUAT_TYPE = 1; // 担保方式
	public static final int DATADIC_MAPTYPE_LNID = 2; // 业务种类细分
	public static final int DATADIC_MAPTYPE_RTN_INTERVAL = 3; // 还款频率
	public static final int DATADIC_MAPTYPE_CLR_CLASS = 4; // 五级分类状态
	public static final int DATADIC_MAPTYPE_TRM_CLASS = 5; // 账户状态
	public static final int DATADIC_MAPTYPE_IDTYPE = 6; // 证件类型
	public static final int DATADIC_MAPTYPE_SEX = 7; // 性别
	public static final int DATADIC_MAPTYPE_MARRIAGE = 8; // 婚姻状况
	public static final int DATADIC_MAPTYPE_EDUCATION = 9; // 最高学历
	public static final int DATADIC_MAPTYPE_OCCUPATION = 10; // 职业
	public static final int DATADIC_MAPTYPE_TRADE = 11; // 单位所属行业
	public static final int DATADIC_MAPTYPE_DUTY = 12; // 职务
	public static final int DATADIC_MAPTYPE_TITLE = 13; // 职称
	public static final int DATADIC_MAPTYPE_HABITATION = 14; // 居住状况

	// 商铺贷款
	public static final String SUBCLASS_PROJ_SHOP = "0";
	// 住房贷款
	public static final String SUBCLASS_PROJ_HOUSE = "1";
	// 汽车贷款
	public static final String SUBCLASS_PROJ_CAR = "2";
	// 耐用消费品贷款
	public static final String SUBCLASS_PROJ_PRODUCT = "3";
	// 旅游贷款
	public static final String SUBCLASS_PROJ_TRIP = "4";
	// 助学贷款
	public static final String SUBCLASS_PROJ_EDUCATION = "5";
	// 委托贷款
	public static final String SUBCLASS_PROJ_CONSIGN = "6";
	// 公积金贷款
	public static final String SUBCLASS_PROJ_HOUSE_CONSIGN = "7";
	// 担保协议
	public static final String SUBCLASS_PROJ_ASSURE = "8";
	// 其他贷款
	public static final String SUBCLASS_PROJ_OTHER = "9";

	/**
	 * 缺省功能代码
	 */
	public static final int FUNC_CODE = 999999;

	/**
	 * 系统全局表系统状态
	 */
	public static final String GLOBAL_INFO_SYSTEM_TYPE_ONLINE = "01"; // 联机环境
	public static final String GLOBAL_INFO_SYSTEM_TYPE_BATCH = "02"; // 报表批量环境
	/**
	 * 全局批量状态
	 */
	public static final String GLOBAL_INFO_STATE_ONLINE = "0";
	public static final String GLOBAL_INFO_STATE_BATCH = "1";
	public static final String GLOBAL_INFO_STATE_ALL = "2";

	/**
	 * 机构拆并各步骤处理标志
	 */
	public static final String MERGEINFO_FLAG_FINISHED = "F"; // 处理成功
	public static final String MERGEINFO_FLAG_FAILED = "E"; // 处理失败
	public static final String MERGEINFO_FLAG_RUNING = "R"; // 正在处理
	public static final String MERGEINFO_FLAG_UNPROCESSED = "N"; // 未处理

	/**
	 * 五级分类
	 */
	public static final String CLR_CLASS_NORMAL = "1";// 正常
	public static final String CLR_CLASS_ATTENTION = "2";// 关注
	public static final String CLR_CLASS_SHADINESS = "4";// 可疑
	public static final String CLR_CLASS_LESSER = "3";// 次级
	public static final String CLR_CLASS_LOSS = "5";// 损失

	/**
	 * 日期格式
	 */
	public static final String DATE_PATTERN = "yyyy-MM-dd";
	public static final String FULLTIME_PATTERN = "yyyy-MM-dd.HH.mm.ss.SSS";

	/**
	 * 缺省用户密码加密密钥
	 */
	public static final String DEFAULT_PASSWORD_KEY = "ruimin.bocompl.Ocean's Fourteen.DWMNTH2CJFLCWL";

	/**
	 * 应计标志
	 */
	public static final String INT_OWE_FLAG_ON = "1";
	public static final String INT_OWE_FLAG_OFF = "2";
	public static final String DATE_PARTTEN = "yyyyMMdd"; // 统一的日期格式

	/* Added by Robin Suo */
	public static final String BATCH_DAY_CLOSE = "0"; // 批量标志，关门批量
	public static final String BATCH_DAY_OPEN = "1"; // 批量标志，开门

	public static final String GLOBAL_STATUS_OPEN = "0"; // 系统状态，0-开门
	public static final String GLOBAL_STATUS_START = "1"; // 系统状态，1-日始批量
	public static final String GLOBAL_STATUS_END = "2"; // 系统状态，2-日终批量

	public static final String CREDIT_CHG_TYPE_NO_EXTENDS = "0";// 0-换生效额度时，不保留历史额度使用信息
	public static final String CREDIT_CHG_TYPE_EXTENDS = "1";// 1-换生效额度时，继承原有额度使用信息

	public static final String RATE_TYPE_YEAR = "1";// 年息
	public static final String RATE_TYPE_MONTH = "2";// 月息
	public static final String RATE_TYPE_DAY = "3";// 日息

}
