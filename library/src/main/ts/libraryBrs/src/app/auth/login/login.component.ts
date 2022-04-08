import { Component, OnInit, TemplateRef, ViewChild } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { NgbModal} from '@ng-bootstrap/ng-bootstrap';
import { Login } from 'src/app/core/services/rest/api/api';
import { UsersService } from 'src/app/core/services/rest/users.service';
import { BasicModalComponent } from 'src/app/shared/elements/modal/basic-modal/basic-modal.component';
import { BookOpinionComponent } from '../book-opinion/book-opinion.component';
import { SurveyComponent } from '../survey/survey.component';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  title="login";
  contactHeight="contactHeight";
  mailInput="mail"
  passwordInput="password"
  inputClass="log-reg";
  iconClass="search"
  text="search"
  btnClass="full"
  rightClass="info"
  leftClass="info"
  textLogin="login"
  textRegister="goToRegister"
  textOpen="open"
  submitBtnType="submit";
  mail="mail";
  pass="password";
  loginForm:FormGroup= new FormGroup({});
  @ViewChild("modalTemplate") template: TemplateRef<any>|undefined|null=null;

  
  constructor(private modalService: NgbModal, private usersService:UsersService, private route: ActivatedRoute, private router: Router) { }

  ngOnInit(): void {
    this.initForm();
  }

  openModal=()=>{
    console.log("abc1")
  //  debugger;
    const modalRef = this.modalService.open(BasicModalComponent);
    modalRef.componentInstance.name = "Modal123";
    modalRef.componentInstance.internalTemplate = this.template;
   // [templateFooSelector]="containerTemplate"
   // (<ExampleModalComponent>modalRef.componentInstance).templateFooSelector = this.template;







  }

  openModal2=()=>{
    console.log("abc")
  //  debugger;
    const modalRef = this.modalService.open(BookOpinionComponent);
    modalRef.componentInstance.name = "Modal1";
  }
    
  openModal3=()=>{
    console.log("abc")
  //  debugger;
    const modalRef = this.modalService.open(SurveyComponent, { size: 'lg' });
    modalRef.componentInstance.name = "Modal1";
    
  }

  initForm(){
    this.loginForm = new FormGroup({
      mail: new FormControl(''),
      password: new FormControl('')
    });
  }

  onSubmit(form:any){
    let login:Login = this.loginForm.value;
    this.usersService.login(login).subscribe(resp=>{
      
      this.router.navigate(['/user/myAccount']);
      console.log(resp)
    })


  }

}
