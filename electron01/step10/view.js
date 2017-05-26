
"use strict"

window.$ = window.jQuery = require('jquery')

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
   memberDao.insert(
   {
    name : fiName.val(),
    tel : fiTel.val(),
    email: fiEmail.val(),
    password : '1111'
   },
   function(result) {
     studentDao.insert(
       {
         no : result.insertId,
         working : (fiWorking.prop('checked') ? 'Y' : 'N'),
         schoolName : fiSchoolName.val()
       },
       function(result) {
         location.href = 'index.html'
       },
       function(error) {
           alert('학생 데이터 등록 중 오류 발생!')
           throw error;
       }) //insertStudent
   },
   function(error) {
     alert('회원 기본 데이터 등록 중 오류 발생!')
     throw error;
   }) //insertMember()
}) //click()


} else {
  $('.bit-new').css('display','none')

  var no = (location.search.substring(1).split('=')[1])

  studentDao.selectOne(no,
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
    memberDao.update(
    {
      no : no,
      name : fiName.val(),
      tel : fiTel.val(),
      email : fiEmail.val()
    },
    function(result) {
      studentDao.update(
      {
      no : no,
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
    }) //updateStudent
    },function(error) {
      alert('회원 기본 데이터 변경 중 오류 발생!')
      throw error;
    }) //updateMember

}) //click()


  $('#del-btn').click(function (){
    connection.query(
      'delete from stud where sno=?',
      [no],
      function(error, result) {
        if (error) {
          alert('학생 데이터 삭제 중 오류 발생!')
          throw error;
        }

    connection.query(
      'delete from memb where mno=?',
      [no],
      function(error, result) {
        if (error) {
          alert('기본 회원 데이터 삭제 중 오류 발생!')
          throw error;
        }
        location.href = 'index.html'

      }) //connection.query
}) //connection.query

}) // click

} //else

$('#lst-btn').click(function () {
  location.href = "index.html"
})

//
