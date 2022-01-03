import { Component, OnInit } from '@angular/core';
import { NgbModal} from '@ng-bootstrap/ng-bootstrap';
import { ExampleModalComponent } from 'src/app/shared/elements/modal/example-modal/example-modal.component';
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

  
  constructor(private modalService: NgbModal) { }

  ngOnInit(): void {
  }

  openModal=()=>{
    console.log("abc")
  //  debugger;
    const modalRef = this.modalService.open(ExampleModalComponent);
    modalRef.componentInstance.name = "Modal";
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

}
