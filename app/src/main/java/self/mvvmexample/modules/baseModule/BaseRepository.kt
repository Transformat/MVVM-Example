package bridgingtech.denefits.modules.baseModule

import self.mvvmexample.utils.MyApplication

abstract class BaseRepository {
    val gson = MyApplication.appInstance?.gson!!
    val jsonParser = MyApplication.appInstance?.jsonParser!!
    val prefs = MyApplication.appInstance?.prefs!!
    val resources = MyApplication.appInstance?.resources!!
    var context = MyApplication.appInstance!!
    var connectionManager = MyApplication.appInstance?.connectionManager!!
}
