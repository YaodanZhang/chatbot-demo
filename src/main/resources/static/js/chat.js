var me = {};
me.avatar = "https://avatars2.githubusercontent.com/u/366630?s=460&v=4";

var you = {};
you.avatar = "https://en.gravatar.com/userimage/64835859/2cfea7a77e469b20dec5ca13d51b2ae9.jpg?size=200";

var user = 'init-user';

function formatAMPM(date) {
  var hours = date.getHours();
  var minutes = date.getMinutes();
  var ampm = hours >= 12 ? 'PM' : 'AM';
  hours = hours % 12;
  hours = hours ? hours : 12; // the hour '0' should be '12'
  minutes = minutes < 10 ? '0'+minutes : minutes;
  var strTime = hours + ':' + minutes + ' ' + ampm;
  return strTime;
}

//-- No use time. It is a javaScript effect.
function insertChat(who, text, time){
  if (time === undefined){
    time = 0;
  }
  var control = "";
  var date = formatAMPM(new Date());

  if (who === "me") {
    control = '<li style="width:100%;">' +
      '<div class="msj-rta macro">' +
      '<div class="text text-r">' +
      '<p>' + text + '</p>' +
      '<p><small>' + date + '</small></p>' +
      '</div>' +
      '<div class="avatar" style="padding:0px 0px 0px 10px !important"><img class="img-circle" style="width:100%;" src="' + me.avatar + '" /></div>' +
      '</li>';
  } else {
    control = '<li style="width:100%">' +
      '<div class="msj macro">' +
      '<div class="avatar"><img class="img-circle" style="width:100%;" src="' + you.avatar + '" /></div>' +
      '<div class="text text-l">' +
      '<p>' + text + '</p>' +
      '<p><small>' + date + '</small></p>' +
      '</div>' +
      '</div>' +
      '</li>';
  }
  setTimeout(
    function(){
      $("ul").append(control).scrollTop($("ul").prop('scrollHeight'));
    }, time);

}

function resetChat(){
  user = Math.random().toString(36).substring(7);
  $("ul").empty();
}

function sendQuestion(question) {
  $.get( "providers/aws/users/" + user + "/questions/" + question, function( data ) {
    insertChat("you", data);
  });
}

$(".mytext").on("keydown", function(e){
  if (e.which == 13){
    var text = $(this).val();
    if (text !== ""){
      insertChat("me", text);
      $(this).val('');
      sendQuestion(text);
    }
  }
});

$('body > div > div > div:nth-child(2) > span').click(function(){
  $(".mytext").trigger({type: 'keydown', which: 13, keyCode: 13});
})

//-- Clear Chat
resetChat();

//-- Print Messages
// insertChat("me", "Hello Tom...", 0);
// insertChat("you", "Hi, Pablo", 1500);
// insertChat("me", "What would you like to talk about today?", 3500);
// insertChat("you", "Tell me a joke",7000);
// insertChat("me", "Spaceman: Computer! Computer! Do we bring battery?!", 9500);
// insertChat("you", "LOL", 12000);


//-- NOTE: No use time on insertChat.
