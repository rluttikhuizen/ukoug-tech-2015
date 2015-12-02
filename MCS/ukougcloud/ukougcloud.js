var uk = require("./lib/ukougimpl");

module.exports = function(service) {


	/**
	 *  The file samples.txt in the archive that this file was packaged with contains some example code.
	 */


	service.post('/mobile/custom/ukougcloud/attendance', function(req,res) {
		var result = {};
		res.send(201, result);
	});

	service.put('/mobile/custom/ukougcloud/attendance', function(req,res) {
		var result = {};
		res.send(200, result);
	});

	service.get('/mobile/custom/ukougcloud/sessions/:id', function(req,res) {
		uk.getSessionById(req,res);
	});

	service.get('/mobile/custom/ukougcloud/speakers', function(req,res) {
            uk.getSpeakers(req,res);
	});

	service.get('/mobile/custom/ukougcloud/sessions', function(req,res) {
		uk.getSessions(req,res);
	});

};
