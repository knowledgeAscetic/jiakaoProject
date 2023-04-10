Commons.baseUrl = 'http://localhost:8080/'

class StorageKey {
    static USER = 'user'
    static TOKEN = 'token'
    static USERNAME = 'username'
    static NICKNAME = 'nickname'
    static ID = 'id'
    static HEADER = 'Token'
}

class ImageType {
    static ROUTE = 1
}

class CourseType {
    static SET = 0
    static K2 = 2
    static K3 = 3
}

class SysResourceType {
    static DIR = 0
    static MENU = 1
    static BTN = 2
    static string(val) {
        switch (val) {
            case this.DIR: return '目录'
            case this.MENU: return '菜单'
            case this.BTN: return '按钮'
        }
    }
}