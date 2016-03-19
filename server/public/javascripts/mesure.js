var app = angular.module("myApp", []);

app.controller("TreeController", ['$scope', function($scope) {

    $scope.delete = function(data) {
        data.nodes = [];
    };

    $scope.addSystem = function(data) {
        var post = data.nodes.length + 1;
        var newName = data.name + post;
        data.nodes.push({
            name: newName, 
            mesure:[],
            nodes: []
        });
    };
	
	$scope.addMesure = function(data, mesure){
		alert(data + " " + mesure);
		data.mesure.push({
			groupeDonnee: mesure
			});
		console.log(data);
		console.log(mesure);
		return false;
	}

    $scope.tree = [{
    	name: "test", 
    	mesure: [], 
    	nodes: []
    }];

}]);