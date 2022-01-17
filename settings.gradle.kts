rootProject.name = "GitApp"
include(":app")

include(":navigator")
include(":settings")
include(":style")

include (":coroutines")
include(":coroutines:appscope")
include(":coroutines:dispatchers")

include (":favorite")
include(":favorite:favoriteui")
include(":favorite:favoritedb")

include (":repos")
include(":repos:reposui")
include(":repos:reposinteractors")

include (":devs")
include(":devs:devsui")

include(":git-domain")
include(":git-domain:base")
include(":git-domain:trending")

include(":components")

include(":core")
include(":paging")
include(":toaster")
include(":internetdetector")

include(":repodetails")
include(":repodetails:repodetailsui")
include(":repodetails:repodetailinteractor")
