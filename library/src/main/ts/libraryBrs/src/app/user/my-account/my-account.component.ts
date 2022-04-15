import { Component, ElementRef, NgZone, OnDestroy, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup } from '@angular/forms';
import { Address, AppUser } from 'src/app/core/services/rest/api/api';
import { UsersService } from 'src/app/core/services/rest/users.service';
declare let $ : any;
@Component({
  selector: 'app-my-account',
  templateUrl: './my-account.component.html',
  styleUrls: ['./my-account.component.scss']
})
export class MyAccountComponent implements OnInit, OnDestroy {
  title="myAccount";
  rulesHeight="rulesHeight";

  inputClass="log-reg";
  iconClass="search";
  text="search";
  btnClass="full";
  rightClass="info";
  leftClass="info";
  textLogin="login";
  textRegister="goToRegister";
  
  mailInput="mail";
  passwordInput="password";
  passwordRepeatInput="passwordRepeat";
  nameInput="name";
  surnameInput="surname";
  phoneInput="phoneNo";
  streetInput="street";
  houseNoInput="houseNo";
  flatNoInput="flatNo";
  postalCodeInput="postalCode";
  cityInput="city";
  mailDisabled=true;
  editTextLogin="edit";
  nameForm="name";
  surnameForm="surname";
  formDisabled=true;
  confirmClass="full orange"
  cancelClass="full gray"
  textConfirm = "submit";
  textCancel = "cancel";
  me=this;
  editForm:FormGroup= this.init();
  userInfo:AppUser={};

  constructor(private element: ElementRef, private userService: UsersService, private fb:FormBuilder){}
  ngOnDestroy(): void {
    this.initForm();
  }

  ngOnInit(): void { 
   // debugger;
     let elements:any = document.getElementsByClassName('dmenu');
     this.initForm();
     //$(elements[0]).css('display', 'none')
  }

  initForm(){
    this.userService.whoAmI().subscribe(userPrincipal=>{
    this.userService.getById(userPrincipal.principal.id).subscribe(user=>{
      console.log(user);
      this.userInfo = user.content;
      let meUser = user.content;
      
      this.fillForm();
    })
    })
    
  }

  onSubmit = (form: any) => {
    console.log(form)
    let userForm = form.value;
    this.userInfo.name = userForm.name;
    this.userInfo.phoneNo = userForm.phoneNo;
    this.userInfo.surname = userForm.surname;
    let address: Address = {};
    address.city = userForm.city;
    address.street = userForm.street;
    address.houseNo = userForm.houseNo;
    address.flatNo = userForm.flatNo;
    address.postcode = userForm.postcode;
    this.userInfo.address = address;

    this.userService.update(this.userInfo).subscribe(response => {
      debugger;
      this.userInfo = response.content;
      //this.fillForm();
 
      this.formDisabled = true;
    })
  }

  init(){
    return this.fb.group({
      name:'',
      surname:'',
      phoneNo:'',
      mail:'',
      street:'',
      houseNo:'',
      flatNo:'',
      postcode:'',
      city:'',
    })
  }

  fillForm(){
    let meUser = this.userInfo;
    let address = this.userInfo.address||{};

    this.editForm = this.fb.group({
      name:meUser.name,
      surname:meUser.surname,
      phoneNo:meUser.phoneNo,
      mail:meUser.mail,
      street:address.street,
      houseNo:address.houseNo,
      flatNo:address.flatNo,
      postcode:address.postcode,
      city:address.city,
    })

  }

  edit=()=>{
    this.formDisabled = false;
  }

  cancel=()=>{

    this.formDisabled = true;
  }

}