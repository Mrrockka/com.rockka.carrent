'use strict'

function registerPayment(invoice_id){
	$.post('/payment/approve' + invoice_id);
}