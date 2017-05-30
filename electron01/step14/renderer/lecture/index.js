"use strict"

window.$ = window.jQuery = require('jquery')

var lectureService = require('electron').remote.getGlobal('lectureService')

var tbody = $('#lecture-tbl > tbody')

displayList(1)

$('#add-btn').click(function(){
  location.href = "view.html"
})

$('#prev-btn').click(function(){
  var currPageNo = parseInt($('#page-no').text())
  displayList(currPageNo - 1)
})

$('#next-btn').click(function(){
  var currPageNo = parseInt($('#page-no').text())
  displayList(currPageNo + 1)
})

function onClickViewLink(event) {
  location.href = 'view.html?no=' + $(this).attr('data-no')
  event.preventDefault()
}

function displayList(pageNo){
  lectureService.list(
    pageNo,
    function(results, totalCount){

    tbody.html('');
    for (var i = 0;i < 3 ; i++){
      if (i < results.length){
        let l = results[i]
        $("<tr>").html("<td>" + l.lno +
        "</td><td><a href='#' data-no='" + l.lno + "' class='view-link'>" + l.titl +
        "</a></td><td>" + l.sdt2 +
        "</td><td>" + l.edt2 +
        "</td><td>" + l.thrs +
        "</td><td>" + l.pric +
        "</td>")
        .appendTo(tbody)
      }else {
        $("<tr><td colspan='5'>&nbsp;</td></tr>").appendTo(tbody)
      }

    }
    $('table .view-link').click(onClickViewLink)

  preparePagingBar(pageNo, totalCount)

  },
  function(error) { //errorFn
    alert('데이터 조회중 오류발생')
    throw error;

  }) //listStudent
} //displayList()



function preparePagingBar(pageNo, totalCount){
  $('#page-no').text(pageNo)

  if(pageNo == 1){
    $('#prev-btn').attr('disabled', true)
  } else {
    $('#prev-btn').attr('disabled', false)
  }

    var totalPage = parseInt(totalCount / 3 + (totalCount % 3 > 0 ? 1 : 0));

    if (pageNo == totalPage) {
      $('#next-btn').attr('disabled', true)
    } else {
      $('#next-btn').attr('disabled', false)
    }


} // function preparePagingBar







/* */