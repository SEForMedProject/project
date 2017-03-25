/**
 * Created by ewrfcas on 2017/3/15.
 */
var app = angular.module('medicalApp',['ui.bootstrap','ngResource']);
app.config(['$locationProvider', function ($locationProvider) {
    $locationProvider.html5Mode({
        enabled: true,
        requireBase: false
    });
}]);
app.factory("diseaseService",["$resource",
    function($resource){
        return $resource({},{},
            {
                search:{
                    method:"GET",
                    url:"/search/getDiseaseByName/",
                    isArray:false
                },
                getDiseaseDetailById:{
                    method:"GET",
                    url:"/disease/getDiseaseDetailById/",
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
            var params={
                diseaseId:$scope.diseaseId
            };
            diseaseService.getDiseaseDetailById(params).$promise.then(function (response) {
                $scope.diseaseDetail=response.data;
            },function(error){
                console.log(error.message);
            });
            $scope.showDetail=true;
            $scope.showDoctors=false;

            //造假数据
            var datatest={
                doctorName:"王大头",
                introduce:"阿奇委屈委屈啊苏打撒旦撒打算当前我区恶趣味无穷"
            };
            $scope.datacars=[];
            for(i=0;i<23;i++){
                var obj=new Object();
                obj.doctorName=datatest.doctorName+i;
                obj.introduce=datatest.introduce+i;
                $scope.datacars.push(obj);
            }
            $scope.currentPage=1;
            $scope.itemsPerPage=5;
            if($scope.datacars==null)$scope.totalItems=0;
            else $scope.totalItems=$scope.datacars.length;
        };

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
        };

        //跳转到疾病页面
        $scope.toDisease=function(index){
            window.location=("../disease?diseaseId="+$scope.diseaseList[index].diseaseId);
        };

        $scope.$watch('currentPage', function() {
            var startOne=($scope.currentPage-1)*$scope.itemsPerPage;
            var endOne=$scope.currentPage*$scope.itemsPerPage;
            if(endOne>$scope.totalItems)endOne=$scope.totalItems;
            $scope.showList=$scope.datacars.slice(startOne,endOne);
        })
    }]);
