import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { AppUser, Book, Opinion } from 'src/app/core/services/rest/api/api';
import { BooksService } from 'src/app/core/services/rest/books.service';
import { UsersService } from 'src/app/core/services/rest/users.service';

@Component({
  selector: 'app-rating-modal',
  templateUrl: './rating-modal.component.html',
  styleUrls: ['./rating-modal.component.scss']
})
export class RatingModalComponent implements OnInit {
  @Input() book: Book={};
  user:AppUser={};
  title = "rating.title";
  info="rating.info"
  labelKeyword = "Słowa kluczowe"
  labelSubject = "Tematyka"
  initialRating = 0;
  ratingForm:FormGroup= this.init();
  
  constructor(public activeModal: NgbActiveModal, public booksService:BooksService, private fb:FormBuilder, private userService: UsersService) { }
  ngOnInit(): void {
    console.log(this.ratingForm)
    this.whoAmI();
  }

  onCancel = () => {
    console.log(this.ratingForm.value)
    //this.activeModal.dismiss('Cross click');
  }

  onConfirm = () => {
    this.save()
    this.activeModal.dismiss('Cross click');
  }

  init(){
    return this.fb.group({
      rating:this.initialRating
    })
  }

  save(){
    let opinion:Opinion = this.ratingForm.value;
    opinion.bookId = this.book.id;
    opinion.userId = this.user.id;

    this.booksService.createOpinion(opinion).subscribe(response=>{
      console.log(response)

    })

//    this.booksService.createChangeProposal().subscribe(response=>{

 //   })

  }

  whoAmI() {
    this.userService.whoAmI().subscribe(userPrincipal => {
      this.user.id = userPrincipal.principal.id;
    })
  }

}
