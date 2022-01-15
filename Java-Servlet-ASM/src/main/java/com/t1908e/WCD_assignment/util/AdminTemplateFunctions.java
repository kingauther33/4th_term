package com.t1908e.WCD_assignment.util;

public class AdminTemplateFunctions {
    public static String getTemplateStyles() {
        return "<link rel=`preconnect` href=`https://fonts.gstatic.com`>\n" +
                "<link href='https://fonts.googleapis.com/css2?family=Nunito:wght@300;400;600;700;800&display=swap' rel='stylesheet'>\n" +
                "<link rel='stylesheet' href='/admin/assets/css/bootstrap.css'>\n" +
                "<link rel='styleshee' href='/admin/assets/vendors/iconly/bold.css'>\n" +
                "<link rel='stylesheet' href='/admin/assets/vendors/perfect-scrollbar/perfect-scrollbar.css'>\n" +
                "<link rel='stylesheet' href='/admin/assets/vendors/bootstrap-icons/bootstrap-icons.css'>\n" +
                "<link rel='stylesheet' href='/admin/assets/css/app.css'>\n";
    }

    public static String getTemplateScripts() {
        return "<script src='/admin/assets/vendors/perfect-scrollbar/perfect-scrollbar.min.js'></script>\n" +
                "<script src='/admin/assets/js/bootstrap.bundle.min.js'></script>\n" +
                "<script src='/admin/assets/js/main.js'></script>\n";
    }

    public static String getLeftMenu() {
        return "    <!--Left menu -->\n" +
                "    <div id='sidebar' class='active'>\n" +
                "        <div class='sidebar-wrapper active'>\n" +
                "            <div class='sidebar-header'>\n" +
                "                <div class='d-flex justify-content-between'>\n" +
                "                    <div class='logo' style='width: 170px; height: 170px'>\n" +
                "                        <a href='/admin'><img style='width: 170px; height: 170px' src='https://res.cloudinary.com/dnby4zyda/image/upload/v1621768189/m2h19du1zslemk2mdvpi.png' alt='Logo' srcset=''></a>\n" +
                "                    </div>\n" +
                "                    <div class='toggler'>\n" +
                "                        <a href='#' class='sidebar-hide d-xl-none d-block'><i class='bi bi-x bi-middle'></i></a>\n" +
                "                    </div>\n" +
                "                </div>\n" +
                "            </div>\n" +
                "            <div class='sidebar-menu'>\n" +
                "                <ul class='menu'>\n" +
                "                    <li class='sidebar-title'>Menu</li>\n" +
                "\n" +
                "                    <li class='sidebar-item active '>\n" +
                "                        <a href='/admin' class='sidebar-link'>\n" +
                "                            <i class='bi bi-grid-fill'></i>\n" +
                "                            <span>Dashboard</span>\n" +
                "                        </a>\n" +
                "                    </li>\n" +
                "\n" +
                "                    <li class='sidebar-item  has-sub'>\n" +
                "                        <a href='#' class='sidebar-link'>\n" +
                "                            <i class='bi bi-stack'></i>\n" +
                "                            <span>Foods</span>\n" +
                "                        </a>\n" +
                "                        <ul class='submenu '>\n" +
                "                            <li class='submenu-item '>\n" +
                "                                <a href='/admin/food/create'>Add New</a>\n" +
                "                            </li>\n" +
                "                            <li class='submenu-item '>\n" +
                "                                <a href='/admin/food'>Browse</a>\n" +
                "                            </li>\n" +
                "                        </ul>\n" +
                "                    </li>\n" +
                "\n" +
                "                    <li class='sidebar-item  has-sub'>\n" +
                "                        <a href='#' class='sidebar-link'>\n" +
                "                            <i class='bi bi-collection-fill'></i>\n" +
                "                            <span>Food Category</span>\n" +
                "                        </a>\n" +
                "                        <ul class='submenu '>\n" +
                "                            <li class='submenu-item '>\n" +
                "                                <a href='/admin/category/create'>Add</a>\n" +
                "                            </li>\n" +
                "                            <li class='submenu-item '>\n" +
                "                                <a href='/admin/category'>Browse</a>\n" +
                "                            </li>\n" +
                "                        </ul>\n" +
                "                    </li>\n" +
                "                </ul>\n" +
                "            </div>\n" +
                "            <button class='sidebar-toggler btn x'><i data-feather='x'></i></button>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "    <!--End Left menu -->";
    }

    public static String getFooter() {
        return "        <footer>\n" +
                "            <div class='footer clearfix mb-0 text-muted'>\n" +
                "                <div class='float-start'>\n" +
                "                    <p>2021 &copy; Huydz</p>\n" +
                "                </div>\n" +
                "                <div class='float-end'>\n" +
                "                    <p>Crafted with <span class='text-danger'><i class='bi bi-heart'></i></span> by <a\n" +
                "                            href='http://ahmadsaugi.com'>Huydz T1908E APTECH</a></p>\n" +
                "                </div>\n" +
                "            </div>\n" +
                "        </footer>";
    }
}
