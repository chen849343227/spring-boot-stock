$('#example').DataTable( {
    "ajax": {
        "url": "http://192.168.31.68:8080/stock/hkall?page=1",
        "type": "POST"
    }
} );