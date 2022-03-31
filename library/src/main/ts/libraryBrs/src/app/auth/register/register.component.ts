import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Address, Register } from 'src/app/core/services/rest/api/api';
import { UsersService } from 'src/app/core/services/rest/users.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {
  title="register";
  rulesHeight="rulesHeight";

  inputClass="log-reg";
  iconClass="search"
  text="search"
  btnClass="full"
  rightClass="info"
  leftClass="info"
  textLogin="login"
  textRegister="createAccount"
  iconClass2="sign-in-alt";
  goLogin="goLogin";
  mailInput="mail"
  passwordInput="password"
  passwordRepeatInput="passwordRepeat"
  nameInput="name"
  surnameInput="surname"
  phoneInput="phoneNo"
  streetInput="street"
  houseNoInput="houseNo"
  flatNoInput="flatNo"
  postalCodeInput="postalCode"
  cityInput="city"
  name="name";
  surname="surname";
  mail="mail";
  phoneNo="phoneNo";
  passwordRepeat="passwordRepeat";
  password="password";
  street="street";
  houseNo="houseNo";
  flatNo="flatNo";
  postcode="postcode";
  city="city";
  submitBtnType="submit";
  registerForm:FormGroup = new FormGroup({});


  
  constructor(private usersService:UsersService, private route: ActivatedRoute, private router: Router) { }

  ngOnInit(): void {
    this.initForm();
  }

  initForm() {
    this.registerForm = new FormGroup({
      mail: new FormControl(''),
      password: new FormControl(''),
      phoneNo: new FormControl(''),
      name: new FormControl(''),
      surname: new FormControl(''),
      passwordRepeat: new FormControl(''),
      street: new FormControl(''),
      houseNo: new FormControl(''),
      flatNo: new FormControl(''),
      postcode: new FormControl(''),
      city: new FormControl(''),
    });
  }

  onSubmit() {
    let register: Register = this.registerForm.value;
    let address:Address = this.registerForm.value;
    register.address = {'street': this.registerForm.value.street,
      'houseNo': this.registerForm.value.houseNo,
      'flatNo': this.registerForm.value.flatNo,
      'postcode': this.registerForm.value.postcode,
      'city': this.registerForm.value.city,
      'district': this.registerForm.value.district,
      'country': this.registerForm.value.country}

  this.usersService.register(register).subscribe(resp => {

      //this.router.navigate(['/user/myAccount']);
      console.log(resp)
    })
  }

}
