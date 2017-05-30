
"use strict"

window.$ = window.jQuery = require('jquery')

var studentDao = createStudentDao(connection)
var memberDao = createMemberDao(connection)
var studentService = createStudentService(memberDao, studentDao)

var fiNo = $('#fi-no'),
    fiEmail = $('#fi-email'),
    fiName = $('#fi-name'),
    fiTel = $('#fi-tel'),
    fiSchoolName = $('#fi-school-name'),
    fiWorking = $('#fi-working');

if (location.search == ""){
  $('.bit-view').css('display','none')

  $('.bit-new').css('display', '')

$('#add-btn').click(function(){  //add버튼에 대해서 클릭 이벤트 리스너를 등록하라
  studentService.insert(
  {
    name : fiName.val(),
    tel : fiTel.val(),
    email: fiEmail.val(),
    password : '1111',
    working : (fiWorking.prop('checked') ? 'Y' : 'N'),
    schoolName : fiSchoolName.val()
  },

  function() {  // 무조건 변수를 선언할필요없다. 아규먼트스에 저장해놓는다.
    location.href = 'index.html'
  },

  function(error) {
    alert('학생 데이터 등록 중 오류 발생!')
    throw error;
  }) // insert()

}) //click()


} else {
  $('.bit-new').css('display','none')

  var no = (location.search.substring(1).split('=')[1])

  studentService.detail(
    no,
    function(result) {
      var student = result
      fiNo.text(student.mno)
      fiEmail.val(student.email)
      fiName.val(student.name)
      fiTel.val(student.tel)
      fiSchoolName.val(student.schl_nm)
      fiWorking.attr('checked', (student.work == 'Y' ? true : false))
    },
    function(error) {
      if (error){
        alert('학생 데이터 가져오는중 오류 발생!')
        throw error
      }
    }) //selectOneStudent

  $('#upd-btn').click(function (){
    studentService.update(
    {
      no : no,
      name : fiName.val(),
      tel : fiTel.val(),
      email : fiEmail.val(),
      working : (fiWorking.prop('checked') ? 'Y' : 'N'),
      schoolName : fiSchoolName.val()
    },
    function(result) {
      alert('변경 하였습니다')
      location.href='index.html'
    },
    function(error) {
    alert('학생 데이터 변경 중 오류 발생!')
    throw error;
    }) //studentService.update()
}) //click()


  $('#del-btn').click(function (){
  studentService.delete(no,
  function(result) {
    location.href = 'index.html'
  }, function(error) {
    alert('기본 회원 데이터 삭제 중 오류 발생!')
    throw error;
  }) // studentService.delete

}) // click

} //else

$('#lst-btn').click(function () {
  location.href = "index.html"
})

//