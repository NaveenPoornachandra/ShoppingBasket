/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function validateEmail() {
    var emailPattern = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;
    alert(emailPattern.test("naveen.poornachandra@gmail.com"));
}

function validateSSN() {
    var ssnPattern = /[0-9]{3}\-?[0-9]{2}\-?[0-9]{4}$/;
    alert(ssnPattern.test("123-121234"));
}

function validateDate() {
    var datePattern = /^\d{1,2}(\-|\/|\.)\d{1,2}\1\d{4}$/;
    alert(datePattern.test("01.01.1982"));
}

function validateAge() {
    var value = $('#age').val();
    var regexp = /^[\d\,\.]*$/;
    if (!regexp.test(value) || value === "")
        alert("Please enter valid value");
    return;
}

function submitAjax() {
    var data = new Object();
    data['param'] = $('#ajaxId').val();
    $.ajax({
        type: 'GET',
        contentType: 'application/json; charset=utf-8',
        url: "http://localhost:8383/ShoppingBasket/JSONServlet",
        dataType: 'json',
        data: data,
        username: "naveen",
        password: "infy@123",
        success: function(data, textStatus, jqXHR) {
            alert(data);
        },
        error: function(jqXHR, textStatus, errorThrown) {
            alert(errorThrown);
        }
    });
}

function customeResponseAjax() {
    var request = $.ajax({
        type: 'POST',
        url: "/ShoppingBasket/CustomResponse",
        accepts: "application/bint,text/xml",
        dataFilter: function(data, type) {
            if (request.getResponseHeader('Content-Type').indexOf('application/bint') !== -1) {
                return parseBint(data);
            } else {
                return parseXml(data);
            }
        },
        success: function(data) {
            alert(data);
        }
    });
}

function parseBint(data) {
    return "Bint";
}

function parseXml(data) {
    return "XML";
}

function uploadAjax() {
    var uploadFile = new XMLHttpRequest();
    var file = $('#ajaxUpload')[0];
    var formData = new FormData();
    formData.append('upload', file.files[0]);


    /*uploadFile.upload.onprogress = function(event){
     if(event.lengthComputabl){
     $('#ajaxProgress').value = (event.loaded/event.total)*100;
     $('#ajaxProgress').textContent = $('#ajaxProgress').value;
     }
     };
     
     uploadFile.upload.oncomplete = function(event){
     alert('alert complete');
     };*/
    uploadFile.upload.addEventListener("progress", uploadProgress, false);
    uploadFile.addEventListener("load", uploadComplete, false);
    uploadFile.addEventListener("progress", uploadProgress, false);

    uploadFile.open("post", "http://localhost:8383/ShoppingBasket/UploadServlet", true);
    uploadFile.setRequestHeader("Content-Type", "multipart/form-data");
    uploadFile.send(formData);  /* Send to server */
}

function uploadProgress(event) {
    if (event.lengthComputabl) {
        $('#ajaxProgress').value = (event.loaded / event.total) * 100;
        $('#ajaxProgress').textContent = $('#ajaxProgress').value;
    }
}

function uploadComplete(event) {
    alert('Complete........');
}

function changeLabel() {
    $('#q7').text("Labe 1");
}

function Loader() {
    this.status = 'ready';
    this.temp = 50;
    this.updateStatus = function(statusUpdate) {
        this.status = statusUpdate;
    };
}

var loader = new Loader();

function loadTemperature() {
    loader.updateStatus('done');
    alert(loader.status);
}

var Employee = function(name, age) {
    this.name = name;
    this.age = age;
    this.print = function() {
        alert(this.name);
        alert(this.age);
        alert(this.getAttendence());
    };
};

Employee.prototype.getAttendence = function() {
    return 20;
};

var employee = new Employee('naveen', 32);

var Consultant = function(name, age) {
    Employee.call(this, name, age);
};

Consultant.prototype = new Employee();
Consultant.prototype.constructor = Consultant;

Consultant.prototype.getAttendence = function() {
    return 30;
};

var consultant = new Consultant('Vinay', 26);

function loadInheritence() {
    employee.print();
    consultant.print();
}

function storeDestination() {
    localStorage.destination = $('#destination').val();
    alert(localStorage.destination);
}

function showDestination() {
    if (localStorage.destination !== null) {
        $('#destination').val(localStorage.destination);
    }
}

var loadQuest = function() {
    alert(customer.getCustomFunction());
    alert(customer.getData());
};

var Customer = function(name, age) {
    this.name = name;
    this.age = age;
};

Customer.prototype.getData = function() {
    return 'hello';
};

Customer.prototype.getCustomFunction = function() {
    return 'Fello';
};

var customer = new Customer();

Function.prototype.construct = function(args) {
    var fconstructor = this;
    fNewConstr = function() {
        fconstructor.apply(this, args);
    };
    fNewConstr.prototype = fconstructor.prototype;
    return new fNewConstr();
};

function MyConstructor() {
    for (var nProp = 0; nProp < arguments.length; nProp++) {
        this["property" + nProp] = arguments[nProp];
    }
}
function learnApply() {
    var myArray = [4, "Hello world!", false];
    var myInstance = MyConstructor.construct(myArray);

    alert(myInstance.property1); // alerts "Hello world!"
    alert(myInstance instanceof MyConstructor); // alerts "true"
    alert(myInstance.constructor); // alerts "MyConstructor"
}

function showInputBox(dynDiv){
    var inputDiv = document.createElement('div');
    inputDiv.innerHTML = "<input type='text' id='newInputBox'>";
    document.getElementById(dynDiv).appendChild(inputDiv);
    
}

function createCanvas(){
    var canvas = document.getElementById('myCanvas');
    var context = canvas.getContext('2d');
    context.fillStyle = 'rgb(255,0,0)';
    context.fillRect(50,50,100,100);
    
    var canvas2 = document.getElementById('myCanvas1');
    var context2 = canvas2.getContext('2d');
    context2.beginPath();
    context2.moveTo(0,0);
    context2.lineTo(200,100);
    context2.stroke();
    
    var canvas3 = document.getElementById('myCanvas2');
    var context3 = canvas3.getContext('2d');
    context3.beginPath();
    context3.arc(95,50,40,0,2*Math.PI);
    context3.stroke();
    
    var canvas4 = document.getElementById('myCanvas3');
    var context4 = canvas4.getContext('2d');
    context4.font='30px Arial';
    context4.fillText('Hello World',10,20);
    context4.strokeText('Hello World',10,50);
    
    var canvas5 = document.getElementById('myCanvas4');
    var context5 = canvas5.getContext('2d');
    var grd=context5.createLinearGradient(0,0,200,0);
    grd.addColorStop(0,"red");
    grd.addColorStop(1,"white");
    context5.fillStyle = grd;
    context5.fillRect(10,10,80,60);
    
    var canvas6 = document.getElementById('myCanvas5');
    var context6 = canvas6.getContext('2d');
    var grd1=context6.createRadialGradient(75,50,5,90,60,100);
    grd1.addColorStop(0,"red");
    grd1.addColorStop(1,"white");
    context6.fillStyle = grd1;
    context6.fillRect(10,10,150,80);
    
    var canvas7 = document.getElementById('myCanvas6');
    var context7 = canvas7.getContext('2d');
    var img = document.getElementById('image1');
    context7.drawImage(img,10,10);
}

function moveLogo(){
    document.getElementById('logo').style.position = 'relative';
    document.getElementById('logo').style.top = '5px';
    
//    document.getElementById('logo').style.top = '-5px';
//    document.getElementById('logo').style.position = 'absolute';
}

var worker;

function startWorker()
{
    if(typeof Worker !== 'undefined'){
        worker = Worker('js/tasks.js');
        worker.onmessage = function(event){
          document.getElementById('result').innerHTML = event.data;  
        };
        worker.postMessage({'cmd': 'start', 'msg': 'Hi'});
    }
}

function stopWorker()
{ 
    worker.postMessage({'cmd': 'stop', 'msg': 'Stopped'});
    worker.terminate();
}
function registerClick(){
    var button = document.getElementById('about');
    button.addEventListener('click',about);
    //button.attachEvent('click',about);
}

function about(){
    alert('about');
}

function determine(value){
    switch(value.constructor){
        case Number:return "Number";
            break;
        case String: return "String";
            break;
            default : return "undefined";
    }
}

function determine2(){
    var length1 = 75;
    var length2 ='75';
    alert((75===length1));
}

function customError(){
    try{
        doWork();
    }catch(err){
        alert(err.message);
    }
}

function doWork(){
    null.indexOf('abc');
    throw new Error("Object Is null",-123456);
}

function Person(fname,lname){
    this.fname = fname;
    this.lname = lname;
    this.address="";
}

Person.prototype.parseAddress = function(data){
  this.address=data;  
};

Person.prototype.loadAddress = function(){
    var that = this;
    $.get("json/customer.json",{},function(data,innerScope) {
                       that.parseAddress(data);
                       alert(that.address);
        });
};

function scopeAjax(){
    var per = new Person('Naveen','Poornachandra');
    per.loadAddress();
    
}

function eventPropogation(){
    var inner = document.getElementById('innerDiv');
    var middle = document.getElementById('middleDiv');
    var outer = document.getElementById('outerDiv');
    inner.addEventListener('click',innerHandler,true);
    middle.addEventListener('click',middleHandler,true);
    outer.addEventListener('click',outerHandler,true);
}

function innerHandler(event){
    alert('Inner'+event.target.id+this.id+$(this).type);
}
function middleHandler(event){
    alert('Middle'+event.target.id+this.id+$(this).type);
    
}
function outerHandler(event){
    alert('Outer'+event.target.id+this.id+$(this).type);
    
}

function turnColors(){
    $('table tr td:nth-child(2) input').focus(function (){
        $(this).parent().next().css("background-color",'#00FF00');
    });
    $('table tr td:nth-child(2) input').blur(function (){
        $(this).parent().next().css("background-color",'#FFFFFF');
    });
}

$(function() {
    $('form[name=decodeForm]').submit(function() {
        alert('Submitting the form');
        var str = $(this).serialize();
        alert(str);
        str = decodeURIComponent(str);
        alert(str);
        return str;
    });
    showDestination();
    createCanvas();
    moveLogo();
    registerClick();
    eventPropogation();
    turnColors();
    var height = "300";
    var arr = [true,false];
    for(var i in arr)
        alert(arr[i]);
});