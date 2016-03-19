var app = angular.module("myApp", []);

app.controller("TreeController", function($scope, $http) {

    $scope.delete = function(data) {
        data.systems = [];
    };

    $scope.addSystem = function(data) {
        data.systems.push({
            name: data.name, 
            process:[]
        });
    };
	
	$scope.addProcess = function(data){
        data.process.push({
            name: data.name, 
            dataGroups:[]
        });
	};
	
	$scope.addDataGroups = function(data, parentProcess){
		console.log(data);
		
		var params = {
			name: data.name,
			entry: !! (data.movement.toUpperCase().indexOf("E")>-1),
			exit: !!(data.movement.toUpperCase().indexOf("X")>-1),
			read: !!(data.movement.toUpperCase().indexOf("R")>-1),
			write: !!(data.movement.toUpperCase().indexOf("W")>-1),		
			comment: data.commentaire
		};
		
		var config = {
			method : "POST",
			url : "/api/datagroups/new/" + parentProcess.id,
			data : $.param(params),
			headers : {'Content-Type':'application/x-www-form-urlencoded'}
		};
		
		$http(config).then(function(resp){
			console.log(resp.data)
			
			parentProcess.data_groups.push(resp.data);
		}, function(){});
		
		/*parentProcess.data_groups.push({
			name: data.name,
			e: !!(data.movement.toUpperCase().indexOf("E")>-1),
			x: !!(data.movement.toUpperCase().indexOf("X")>-1),
			r: !!(data.movement.toUpperCase().indexOf("R")>-1),
			w: !!(data.movement.toUpperCase().indexOf("W")>-1),		
			commentaire: data.commentaire
        });
		data.name = "";
		data.movement = "";
		data.commentaire = "";*/
	};
	
    $scope.mesure = [/*{
		name: "mesureName",
		systems : [{
			name: "systemsName", 
			process: [{
				name:"processName",
				dataGroups: [{
					name: "dataGroupsName1",
					e: true,
					x: true,
					r: false,
					w: true,
					commentaire: "testcommentaire"
				},{
					name: "dataGroupsName2",
					e: false,
					x: false,
					r: false,
					w: false,
					commentaire: "testcommentaire2"
				}]
			}]
		}]
	}*/];
	$http.get("/api/systems").then(function(resp){
		console.log(resp.data.systems)
		$scope.mesure = [{systems: resp.data.systems}];
	}, function(){});

});