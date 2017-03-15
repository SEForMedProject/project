/**
 * Created by ewrfcas on 2017/3/15.
 */
var app = angular.module('medicalApp',['ui.bootstrap','ngResource']);
app.config(['$locationProvider', function ($locationProvider) {
    $locationProvider.html5Mode({
        enabled: true,
        requireBase: false
    });
}])
app.factory("diseaseService",["$resource",
    function($resource){
        return $resource({},{},
            {
                search:{
                    method:"GET",
                    url:"/search/getDiseaseByName/",
                    isArray:false
                }
            }
        )
    }]);
app.controller("diseaseCtrl", ["$scope","diseaseService",
    function ($scope,diseaseService) {
        function GetRequest() {
            var url = location.search; //获取url中"?"符后的字串
            var theRequest = new Object();
            if (url.indexOf("?") != -1) {
                var str = url.substr(1);
                strs = str.split("&");
                for(var i = 0; i < strs.length; i ++) {
                    theRequest[strs[i].split("=")[0]]=unescape(strs[i].split("=")[1]);
                }
            }
            return theRequest;
        }
        /**
         * 初始化
         */
        $scope.init = function () {
            var request = GetRequest();
            $scope.diseaseId=request["diseaseId"];
            console.log($scope.diseaseId);
        }

        //搜索
        $scope.search = function(){
            var params={
                diseaseName:$scope.diseaseName
            };
            diseaseService.search(params).$promise.then(function (response) {
                $scope.diseaseList=response.data;
            },function(error){
                console.log(error.message);
            });
        }

        //跳转到疾病页面
        $scope.toDisease=function(index){
            window.location=("../disease?diseaseId="+$scope.diseaseList[index].diseaseId);
        }
    }]);
