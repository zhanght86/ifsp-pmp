/**
 * 自定义扩展正则表达式
 */

/**16进制*/
UIKit.rules.isNumOrUpAF = "^[0-9A-F]+$";
/**16进制，至少10位**/
UIKit.rules.isNumOrUpAF10P = "^[0-9A-F]{10,}$";
/**2位16进制**/
UIKit.rules.isNumOrUpAF2 = "^[0-9A-F]{2}$";
/**4位16进制**/
UIKit.rules.isNumOrUpAF4 = "^[0-9A-F]{4}$";
/**8位16进制**/
UIKit.rules.isNumOrUpAF8 = "^[0-9A-F]{8}$";
/**10位16进制**/
UIKit.rules.isNumOrUpAF10 = "^[0-9A-F]{10}$";
/**12位16进制**/
UIKit.rules.isNumOrUpAF12 = "^[0-9A-F]{12}$";

UIKit.rules.isNum = "^\\d$";
/** 16位数字 **/
UIKit.rules.isNum16 = "^[0-9]{16}$";

/** 22位数字 **/
UIKit.rules.isNumOrWord22 = "^[0-9A-Za-z]{22}$";

UIKit.rules.isNum6 = "^\\d{6}$";
