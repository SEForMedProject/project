/**
 * Created by ewrfcas on 2017/3/3.
 */
var app = angular.module('medicalApp',['ui.bootstrap','ngResource']);
app.factory("indexService",["$resource",
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
app.controller("indexCtrl", ["$scope","indexService",
    function ($scope,indexService) {
        /**
         * 初始化
         */
        $scope.init = function () {
            console.log("init success");
        }

        //搜索
        $scope.search = function(){
            var params={
                diseaseName:$scope.diseaseName
            };
            indexService.search(params).$promise.then(function (response) {
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
