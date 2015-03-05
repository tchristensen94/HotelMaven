/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function getUrlParameter(sParam)
{
    var sPageURL = window.location.search.substring(1);
    var sURLVariables = sPageURL.split('&');
    for (var i = 0; i < sURLVariables.length; i++)
    {
        var sParameterName = sURLVariables[i].split('=');
        if (sParameterName[0] === sParam)
        {
            return sParameterName[1];
        }
    }
}
$(document).ready(function () {
    var id = $('#hotelID').val();
    if(id !== "") {
        $("#createButton").hide();
    } else {
        $("#deleteButton").hide();
        $("#updateButton").hide();
    }
});
$("#updateButton").click(function () {
    $('#option').val('update');
    //$('#form-hotel').submit();
});
$('#create').click(function () {
   var id = $('#hotelID').val();
    var name = $('#hotelName').val();
    var address = $('#hotelAddress').val();
    var city = $('#hotelCity').val();
    var state = $('#hotelState').val();
    var zip = $('#hotelZip').val();
    var note = $('#hotelNotes').val();

    if (name.length < 2 || address.lenght < 2 || city.length < 1 || address < 2 || zip < 1 || note < 1) {

    } else {
        $('#hotelForm').attr("action", "?id=" + id + "&name=" + name + "&address=" + address + "&city=" + city + "&state=" + state + "&zip=" + zip + "&note=" + note + "&option=create");
        $('#hotelForm').submit();
    }
});
$('#delete').click(function () {
    var id = $('#hotelID').val();
    var r = confirm("Confirm Deletion");
    if (r) {
        $('#hotelForm').attr("action", "?id=" + id + "&option=delete");
        $('#hotelForm').submit();
    } else {

    }
});


