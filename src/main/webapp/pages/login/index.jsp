<%@page import="com.ruimin.ifs.framework.utils.WebCfgUtil"%>
<%@page import="com.ruimin.ifs.util.CommonConstants2"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/tags/snowweb" prefix="snow"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<title>证通二维码支付管理平台</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
<!-- <link rel="Shortcut Icon" href="<%= request.getContextPath()%>/pages/login/imgs/favicon.ico" type="image/x-icon" /> -->
<!-- bootstrap & fontawesome -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/public/lib/ace/css/bootstrap.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/public/lib/fontawesome/css/font.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/public/lib/fontawesome/css/font-awesome.css" />
<!-- page specific plugin styles -->
<!-- text fonts -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/public/lib/ace/css/ace-fonts.css" />
<!-- ace styles -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/public/lib/ace/css/ace.css" class="ace-main-stylesheet" id="main-ace-style" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/public/theme/Aqua/css/ligerui-menu.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/public/theme/Aqua/css/ligerui-tab.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/public/theme/Aqua/css/ligerui-dialog.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/public/theme/Ace/css/style.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/pages/login/css/index.css" />
<!--[if lte IE 9]>
			<link rel="stylesheet" href="${pageContext.request.contextPath}/public/lib/ace/css/ace-part2.css" class="ace-main-stylesheet" />
		<![endif]-->
<!--[if lte IE 9]>
		  <link rel="stylesheet" href="${pageContext.request.contextPath}/public/lib/ace/css/ace-ie.css" />
		<![endif]-->
<!-- inline styles related to this page -->
<!-- ace settings handler -->
<script src="${pageContext.request.contextPath}/public/lib/ace/js/ace-extra.js"></script>

<!-- HTML5shiv and Respond.js for IE8 to support HTML5 elements and media queries -->

<!--[if lte IE 8]>
		<script src="${pageContext.request.contextPath}/public/lib/ace/js/html5shiv.js"></script>
		<script src="${pageContext.request.contextPath}/public/lib/ace/js/respond.js"></script>
		<![endif]-->
</head>

<body class="no-skin">
	<!-- #section:basics/navbar.layout -->
	<div id="navbar" class="navbar navbar-default">
		<script type="text/javascript">
			try {
				ace.settings.check('navbar', 'fixed')
			} catch (e) {
			}
		</script>

		<div class="navbar-container" id="navbar-container">
			<!-- #section:basics/sidebar.mobile.toggle -->
			<button type="button" class="navbar-toggle menu-toggler pull-left" id="menu-toggler" data-target="#sidebar">
				<span class="sr-only">Toggle sidebar</span> <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span>
			</button>

			<!-- /section:basics/sidebar.mobile.toggle -->
			<div class="navbar-header pull-left">
				<!-- #section:basics/navbar.layout.brand <i class="main-logo"></i>-->
				<a href="javascript:void(0)" class="navbar-brand"> <small>   证通二维码支付管理平台
				</small>
				</a>

				<!-- /section:basics/navbar.layout.brand -->

				<!-- #section:basics/navbar.toggle -->

				<!-- /section:basics/navbar.toggle -->
			</div>

			<!-- #section:basics/navbar.dropdown -->
			<div class="navbar-buttons navbar-header pull-right" role="navigation">
				<ul class="nav ace-nav">
					<!--<li class="grey" onclick="afterBatchExport()"><a data-toggle="dropdown" class="dropdown-toggle" href="javascript:void(0)" title="我的下载任务"> <i class="ace-icon fa fa-tasks"></i> <span class="badge badge-grey"></span>
					</a>  <ul class="dropdown-menu-right dropdown-navbar dropdown-menu dropdown-caret dropdown-close">
							<li class="dropdown-header"><i class="ace-icon fa fa-check"></i> 4 Tasks to complete</li>

							<li class="dropdown-content">
								<ul class="dropdown-menu dropdown-navbar">
									<li><a href="javascript:void(0)">
											<div class="clearfix">
												<span class="pull-left">Software Update</span> <span class="pull-right">65%</span>
											</div>

											<div class="progress progress-mini">
												<div style="width: 65%" class="progress-bar"></div>
											</div>
									</a></li>

									<li><a href="javascript:void(0)">
											<div class="clearfix">
												<span class="pull-left">Hardware Upgrade</span> <span class="pull-right">35%</span>
											</div>

											<div class="progress progress-mini">
												<div style="width: 35%" class="progress-bar progress-bar-danger"></div>
											</div>
									</a></li>

									<li><a href="javascript:void(0)">
											<div class="clearfix">
												<span class="pull-left">Unit Testing</span> <span class="pull-right">15%</span>
											</div>

											<div class="progress progress-mini">
												<div style="width: 15%" class="progress-bar progress-bar-warning"></div>
											</div>
									</a></li>

									<li><a href="javascript:void(0)">
											<div class="clearfix">
												<span class="pull-left">Bug Fixes</span> <span class="pull-right">90%</span>
											</div>

											<div class="progress progress-mini progress-striped active">
												<div style="width: 90%" class="progress-bar progress-bar-success"></div>
											</div>
									</a></li>
								</ul>
							</li>

							<li class="dropdown-footer"><a href="javascript:void(0)"> See tasks with details <i class="ace-icon fa fa-arrow-right"></i>
							</a></li>
						</ul> </li>-->

					<!--<li class="purple"><a data-toggle="dropdown" class="dropdown-toggle" href="javascript:void(0)"> <i class="ace-icon fa fa-bell icon-animated-bell"></i> <span class="badge badge-important">8</span>
					</a>  <ul class="dropdown-menu-right dropdown-navbar navbar-pink dropdown-menu dropdown-caret dropdown-close">
							<li class="dropdown-header"><i class="ace-icon fa fa-exclamation-triangle"></i> 8 Notifications</li>

							<li class="dropdown-content">
								<ul class="dropdown-menu dropdown-navbar navbar-pink">
									<li><a href="javascript:void(0)">
											<div class="clearfix">
												<span class="pull-left"> <i class="btn btn-xs no-hover btn-pink fa fa-comment"></i> New Comments
												</span> <span class="pull-right badge badge-info">+12</span>
											</div>
									</a></li>

									<li><a href="javascript:void(0)"> <i class="btn btn-xs btn-primary fa fa-user"></i> Bob just signed up as an editor ...
									</a></li>

									<li><a href="javascript:void(0)">
											<div class="clearfix">
												<span class="pull-left"> <i class="btn btn-xs no-hover btn-success fa fa-shopping-cart"></i> New Orders
												</span> <span class="pull-right badge badge-success">+8</span>
											</div>
									</a></li>

									<li><a href="javascript:void(0)">
											<div class="clearfix">
												<span class="pull-left"> <i class="btn btn-xs no-hover btn-info fa fa-twitter"></i> Followers
												</span> <span class="pull-right badge badge-info">+11</span>
											</div>
									</a></li>
								</ul>
							</li>

							<li class="dropdown-footer"><a href="javascript:void(0)"> See all notifications <i class="ace-icon fa fa-arrow-right"></i>
							</a></li>
						</ul> </li>-->

					<%--<li class="green"><a data-toggle="dropdown" class="dropdown-toggle" href="javascript:void(0)"> <i class="ace-icon fa fa-envelope icon-animated-vertical"></i> <span class="badge badge-success">5</span>
					</a>  <ul class="dropdown-menu-right dropdown-navbar dropdown-menu dropdown-caret dropdown-close">
							<li class="dropdown-header"><i class="ace-icon fa fa-envelope-o"></i> 5 Messages</li>

							<li class="dropdown-content">
								<ul class="dropdown-menu dropdown-navbar">
									<li><a href="javascript:void(0)" class="clearfix"> <img src="${pageContext.request.contextPath}/public/lib/ace/avatars/avatar.png" class="msg-photo" alt="Alex's Avatar" /> <span class="msg-body"> <span class="msg-title"> <span
													class="blue">Alex:</span> Ciao sociis natoque penatibus et auctor ...
											</span> <span class="msg-time"> <i class="ace-icon fa fa-clock-o"></i> <span>a moment ago</span>
											</span>
										</span>
									</a></li>

									<li><a href="javascript:void(0)" class="clearfix"> <img src="${pageContext.request.contextPath}/public/lib/ace/avatars/avatar3.png" class="msg-photo" alt="Susan's Avatar" /> <span class="msg-body"> <span class="msg-title"> <span
													class="blue">Susan:</span> Vestibulum id ligula porta felis euismod ...
											</span> <span class="msg-time"> <i class="ace-icon fa fa-clock-o"></i> <span>20 minutes ago</span>
											</span>
										</span>
									</a></li>

									<li><a href="javascript:void(0)" class="clearfix"> <img src="${pageContext.request.contextPath}/public/lib/ace/avatars/avatar4.png" class="msg-photo" alt="Bob's Avatar" /> <span class="msg-body"> <span class="msg-title"> <span
													class="blue">Bob:</span> Nullam quis risus eget urna mollis ornare ...
											</span> <span class="msg-time"> <i class="ace-icon fa fa-clock-o"></i> <span>3:15 pm</span>
											</span>
										</span>
									</a></li>

									<li><a href="javascript:void(0)" class="clearfix"> <img src="${pageContext.request.contextPath}/public/lib/ace/avatars/avatar2.png" class="msg-photo" alt="Kate's Avatar" /> <span class="msg-body"> <span class="msg-title"> <span
													class="blue">Kate:</span> Ciao sociis natoque eget urna mollis ornare ...
											</span> <span class="msg-time"> <i class="ace-icon fa fa-clock-o"></i> <span>1:33 pm</span>
											</span>
										</span>
									</a></li>

									<li><a href="javascript:void(0)" class="clearfix"> <img src="${pageContext.request.contextPath}/public/lib/ace/avatars/avatar5.png" class="msg-photo" alt="Fred's Avatar" /> <span class="msg-body"> <span class="msg-title"> <span
													class="blue">Fred:</span> Vestibulum id penatibus et auctor ...
											</span> <span class="msg-time"> <i class="ace-icon fa fa-clock-o"></i> <span>10:09 am</span>
											</span>
										</span>
									</a></li>
								</ul>
							</li>

							<li class="dropdown-footer"><a href="inbox.html"> See all messages <i class="ace-icon fa fa-arrow-right"></i>
							</a></li>
						</ul> </li>--%>

					<!-- #section:basics/navbar.user_menu -->
					<li class="light-blue"><a data-toggle="dropdown" href="javascript:void(0)" class="dropdown-toggle"> <img class="nav-user-photo" src="${pageContext.request.contextPath}/public/lib/ace/avatars/user.jpg" alt="Jason's Photo" /> <span
							class="user-info"> <small>Welcome,</small> <%=SessionUtil.getSessionUserInfo(request).getTlrno()%>
						</span> <i class="ace-icon fa fa-caret-down"></i>
					</a>

						<ul class="user-menu dropdown-menu-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
						<!--	<li><a href="javascript:void(0)"> <i class="ace-icon fa fa-cog"></i> 设置
							</a></li>
						-->
						<!--	<li><a href="javascript:void(0)" onclick="doChangePwd()"> <i class="ace-icon fa fa-user"></i> 修改密码
							</a></li>
						-->
							<li class="divider"></li>

							<li><a href="javascript:void(0)" onclick="doLogout()"> <i class="ace-icon fa fa-power-off"></i> 退出
							</a></li>
						</ul></li>

					<!-- /section:basics/navbar.user_menu -->
				</ul>
			</div>

			<!-- /section:basics/navbar.dropdown -->
		</div>
		<!-- /.navbar-container -->
	</div>

	<!-- /section:basics/navbar.layout -->
	<div class="main-container" id="main-container">
		<script type="text/javascript">
			try {
				ace.settings.check('main-container', 'fixed')
			} catch (e) {
			}
		</script>

		<div id="sidebar" class="sidebar responsive">
				<script type="text/javascript">
					try { ace.settings.check('sidebar', 'fixed') } catch (e) { }
				</script>
			 	<!--<div class="sidebar-shortcuts" id="sidebar-shortcuts">
					 <div class="sidebar-shortcuts-large" id="sidebar-shortcuts-large">
						<button class="btn btn-success">
							<i class="ace-icon fa fa-signal"></i>
						</button>
						<button class="btn btn-info">
							<i class="ace-icon fa fa-pencil"></i>
						</button>
						<button class="btn btn-warning">
							<i class="ace-icon fa fa-users"></i>
						</button>
						<button class="btn btn-danger">
							<i class="ace-icon fa fa-cogs"></i>
						</button>
				 	</div>
					<div class="sidebar-shortcuts-mini" id="sidebar-shortcuts-mini">
						<span class="btn btn-success"></span> <span class="btn btn-info"></span> <span class="btn btn-warning"></span> <span class="btn btn-danger"></span>
					</div>
				</div> -->
				<%@ include file="left_menu.jsp"%>
				<script type="text/javascript">
					try { ace.settings.check('sidebar', 'collapsed') } catch (e) { }
				</script>
		</div>

		<!-- /section:basics/sidebar -->
		<div class="main-content">
			<div class="main-content-inner">
				<snow:tabs id="main" border="false" showswitch="true">
					<snow:tab title="主页" id="home" closable="false" url="/pages/login/home1.jsp" />
				</snow:tabs>
			</div>
			<!-- /.main-content -->

		</div>
		<!-- /.main-container -->

		<!-- basic scripts -->

		<!--[if !IE]> -->
		<script src="${pageContext.request.contextPath}/public/lib/ace/js/jquery.js"></script>
		<!-- <![endif]-->

		<!--[if IE]>
	<script src="${pageContext.request.contextPath}/public/lib/ace/js/jquery1x.js"></script>
	<![endif]-->
		<script type="text/javascript">
			if ('ontouchstart' in document.documentElement) {
				document.write("${pageContext.request.contextPath}/public/lib/ace/js/jquery.mobile.custom.js'>" + "<"+"/script>");
			}
		</script>
		<script src="${pageContext.request.contextPath}/public/lib/ace/js/bootstrap.js"></script>

		<!-- page specific plugin scripts -->

		<!--[if lte IE 8]>
		  <script src="${pageContext.request.contextPath}/public/lib/ace/js/excanvas.js"></script>
		<![endif]-->
		<script src="${pageContext.request.contextPath}/public/lib/ace/js/jquery-ui.custom.js"></script>
		<script src="${pageContext.request.contextPath}/public/lib/ace/js/jquery.ui.touch-punch.js"></script>
		<script src="${pageContext.request.contextPath}/public/lib/ace/js/jquery.easypiechart.js"></script>
		<script src="${pageContext.request.contextPath}/public/lib/ace/js/jquery.sparkline.js"></script>
		<script src="${pageContext.request.contextPath}/public/lib/ace/js/flot/jquery.flot.js"></script>
		<script src="${pageContext.request.contextPath}/public/lib/ace/js/flot/jquery.flot.pie.js"></script>
		<script src="${pageContext.request.contextPath}/public/lib/ace/js/flot/jquery.flot.resize.js"></script>

		<!-- ace scripts -->
		<script src="${pageContext.request.contextPath}/public/lib/ace/js/elements.scroller.js"></script>
		<script src="${pageContext.request.contextPath}/public/lib/ace/js/elements.colorpicker.js"></script>
		<script src="${pageContext.request.contextPath}/public/lib/ace/js/elements.fileinput.js"></script>
		<script src="${pageContext.request.contextPath}/public/lib/ace/js/elements.typeahead.js"></script>
		<script src="${pageContext.request.contextPath}/public/lib/ace/js/elements.wysiwyg.js"></script>
		<script src="${pageContext.request.contextPath}/public/lib/ace/js/elements.spinner.js"></script>
		<script src="${pageContext.request.contextPath}/public/lib/ace/js/elements.treeview.js"></script>
		<script src="${pageContext.request.contextPath}/public/lib/ace/js/elements.wizard.js"></script>
		<script src="${pageContext.request.contextPath}/public/lib/ace/js/elements.aside.js"></script>
		<script src="${pageContext.request.contextPath}/public/lib/ace/js/ace.js"></script>
		<script src="${pageContext.request.contextPath}/public/lib/ace/js/ace.ajax-content.js"></script>
		<script src="${pageContext.request.contextPath}/public/lib/ace/js/ace.touch-drag.js"></script>
		<script src="${pageContext.request.contextPath}/public/lib/ace/js/ace.sidebar.js"></script>
		<script src="${pageContext.request.contextPath}/public/lib/ace/js/ace.sidebar-scroll-1.js"></script>
		<script src="${pageContext.request.contextPath}/public/lib/ace/js/ace.submenu-hover.js"></script>
		<script src="${pageContext.request.contextPath}/public/lib/ace/js/ace.widget-box.js"></script>
		<script src="${pageContext.request.contextPath}/public/lib/ace/js/ace.settings.js"></script>
		<script src="${pageContext.request.contextPath}/public/lib/ace/js/ace.settings-rtl.js"></script>
		<script src="${pageContext.request.contextPath}/public/lib/ace/js/ace.settings-skin.js"></script>
		<script src="${pageContext.request.contextPath}/public/lib/ace/js/ace.widget-on-reload.js"></script>
		<script src="${pageContext.request.contextPath}/public/lib/ace/js/ace.searchbox-autocomplete.js"></script>

		<!-- inline scripts related to this page -->
		<script type="text/javascript">
			var currentTicket;
			function tabs_main_onFocus(tabid) {
				var $tab = tabs_main.getSelected();
				var ticket = $tab.attr("_ticket_");
				if (ticket && currentTicket != ticket) {
					dwr.engine.setAsync(false);
					SessionTicketProcess.setAciveTicket(ticket);
					DwrFunctions.setCurrentTabFuncId(tabid);
					dwr.engine.setAsync(true);
					//callWebSession && callWebSession(ticket, "change");
					currentTicket = ticket;
				}
			}
			var tabMax = <%=WebCfgUtil.getWebCfgIntValue("maxTabCount")%>;
			var existIsRef = false;
			function doWork(id, title, url, options) {
				if (url && url != "null") {
					if (url.indexOf("_blank:") == 0) {
						window.open(_application_root + url.substring(7));
					} else {
						if(tabs_main.isExist(id)){
							tabs_main.select(id);
							if(existIsRef){
								tabs_main.refresh(id);
							}
							selectMenu(id);
						}else{
							if(tabMax<=0){
								tabs_main.open(id, title, url, options);
								selectMenu(id);
							}else{
								if( tabs_main.length()<=tabMax){
									tabs_main.open(id, title, url, options);
									selectMenu(id);
								}else{
									$.warn("只能同时打开["+tabMax+"]个页面!");
								}
							}
						}
					}
				}
			}
			function doChangePwd() {
				doWork("999900", "修改密码", "/pages/login/changePwd.jsp");
			}
			function doLogout() {
				top.location.href ="<%=CommonConstants2.getProperty("casLogonOut")%>";
			}

			function afterBatchExport() {
				doWork("999901", "批量导出任务列表", "/pages/mng/mydownload.jsp");
			}

			jQuery(function($) {
				function resizePage(width, height, fn) {
					$("#main-container").height(height - 45);
					$(".main-content-inner").height(height - 45);
					fn && fn();
				}
				setTimeout(function() {
					var windowHeight = $(window).height();
					var windowWidth = $(window).width();
					resizePage(windowWidth, windowHeight, function() {
						initElements($('.main-content')[0]);
					});
				}, 0);
				$(window).resize(function() {
					var windowHeight = $(window).height();
					var windowWidth = $(window).width();
					resizePage(windowWidth, windowHeight);
				});

				//////////////////////////////////////
				$('.dialogs,.comments').ace_scroll({
					size : 300
				});

				//Android's default browser somehow is confused when tapping on label which will lead to dragging the task
				//so disable dragging when clicking on label
				var agent = navigator.userAgent.toLowerCase();
				if ("ontouchstart" in document && /applewebkit/.test(agent) && /android/.test(agent))
					$('#tasks').on('touchstart', function(e) {
						var li = $(e.target).closest('#tasks li');
						if (li.length == 0)
							return;
						var label = li.find('label.inline').get(0);
						if (label == e.target || $.contains(label, e.target))
							e.stopImmediatePropagation();
					});

				$('#tasks').sortable({
					opacity : 0.8,
					revert : true,
					forceHelperSize : true,
					placeholder : 'draggable-placeholder',
					forcePlaceholderSize : true,
					tolerance : 'pointer',
					stop : function(event, ui) {
						//just for Chrome!!!! so that dropdowns on items don't appear below other items after being moved
						$(ui.item).css('z-index', 'auto');
					}
				});
				$('#tasks').disableSelection();
				$('#tasks input:checkbox').removeAttr('checked').on('click', function() {
					if (this.checked)
						$(this).closest('li').addClass('selected');
					else
						$(this).closest('li').removeClass('selected');
				});

				//show the dropdowns on top or bottom depending on window height and menu position
				$('#task-tab .dropdown-hover').on('mouseenter', function(e) {
					var offset = $(this).offset();

					var $w = $(window)
					if (offset.top > $w.scrollTop() + $w.innerHeight() - 100)
						$(this).addClass('dropup');
					else
						$(this).removeClass('dropup');
				});

			})
		</script>

		<!-- the following scripts are used in demo only for onpage help and you don't need them -->

		<script type="text/javascript">
			ace.vars['base'] = _application_root = "${pageContext.request.contextPath}";
		</script>
		<script src="${pageContext.request.contextPath}/public/lib/ace/js/elements.onpage-help.js"></script>
		<script src="${pageContext.request.contextPath}/public/lib/ace/js/ace.onpage-help.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/public/lib/ligerui/base.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/public/lib/ligerui/ligerMenu.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/public/lib/ligerui/ligerTab.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/public/lib/ligerui/ligerDialog.js"></script>
		<!-- 
		 -->
		<script src="${pageContext.request.contextPath}/public/js/render.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/dwr/engine.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/dwr/util.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/public/js/dwr.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/dwr/interface/DwrFunctions.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/dwr/interface/SessionTicketProcess.js"></script>
</body>
</html>
