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
                },
                getDoctorByDiseaseName:{
                    method:"GET",
                    url:"/doctor/getDoctorByDiseaseName",
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
            $scope.dataReady=false;
            $scope.diseaseId=request["diseaseId"];
            //获取疾病信息
            var params={
                diseaseId:$scope.diseaseId
            };
            diseaseService.getDiseaseDetailById(params).$promise.then(function (response) {
                $scope.diseaseDetail=response.data;
                //获取相关医生
                var params={
                    diseaseName:$scope.diseaseDetail.name
                };
                diseaseService.getDoctorByDiseaseName(params).$promise.then(function (response) {
                    $scope.doctorList=response.data;
                    $scope.showDetail=true;
                    $scope.showDoctors=false;
                    $scope.currentPage=1;
                    $scope.itemsPerPage=5;
                    if($scope.doctorList==null)$scope.totalItems=0;
                    else $scope.totalItems=$scope.doctorList.length;
                    $scope.dataReady=true;
                },function(error){
                    console.log(error.message);
                });
            },function(error){
                console.log(error.message);
            });
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
            if($scope.dataReady){
                var startOne=($scope.currentPage-1)*$scope.itemsPerPage;
                var endOne=$scope.currentPage*$scope.itemsPerPage;
                if(endOne>$scope.totalItems)endOne=$scope.totalItems;
                $scope.showList=$scope.doctorList.slice(startOne,endOne);
            }
        })
    }]);