var app = angular.module("myApp", []);

app.controller("TreeController", ['$scope', function($scope) {

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
            process:[]
        });
	};
	
	$scope.addDataGroups = function(data, parentProcess){
		console.log(parentProcess);
		parentProcess.dataGroups.push({
			name: data.name,
			e: !!(data.movement.toUpperCase().indexOf("E")>-1),
			x: !!(data.movement.toUpperCase().indexOf("X")>-1),
			r: !!(data.movement.toUpperCase().indexOf("R")>-1),
			w: !!(data.movement.toUpperCase().indexOf("W")>-1),		
			commentaire: data.commentaire
        });
		data.name = "";
		data.movement = "";
		data.commentaire = "";
	};
	$scope.returnStartEditing = function(e){
		if(e.keyCode == 9)
			event.preventDefault();
		dataGroupEntery.focus();
	}
    $scope.mesure = [{
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
	}];

}]);