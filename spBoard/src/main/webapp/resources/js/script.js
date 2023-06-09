$(function() {
	$("#content").summernote({
		placeholder : '내용을 입력하세요.',
		theme: 'darkly',
		height: 400,
		lang: 'ko-KR',
		spellCheck: "false",
		toolbar: [
          ['style', ['style']],
          ['font', ['bold', 'underline', 'clear']],
          ['color', ['color']],
          ['para', ['ul', 'ol', 'paragraph']],
          ['table', ['table']],
          ['insert', ['link', 'picture', 'video']],
          ['view', ['fullscreen', 'codeview', 'help']]
        ]
	});
	
})