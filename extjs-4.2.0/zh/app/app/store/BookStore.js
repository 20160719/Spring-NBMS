// JavaScript DocumentBookStore

	var bookdata = {
		books: [{
			bookId: '11',
			bookName: 'Java编程',
			bookAuthor: '李佳',
			bookCreateTime: '2017-05-05',
			bookModifyTime: '2017-05-05',
			bookRemark: '2017-05-05'
		},{
			bookId: '12',
			bookName: 'C++编程',
			bookAuthor: '张三',
			bookCreateTime: '2017-05-05',
			bookModifyTime: '2017-05-05',
			bookRemark: '2017-05-05'
		}]
	};
	
	Ext.define('Demo.store.BookStore',{
		extend:'Ext.data.Store',
		requires:'Demo.model.BookModel',
		model:'Demo.model.BookModel',
		proxy: {
			type: 'memory',
			data : bookdata,
			reader: {
				type: 'json',
				root: 'books'
			}
		},
		autoLoad: true
	});