import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Book } from 'src/app/core/services/rest/api/api';
import { BooksService } from 'src/app/core/services/rest/books.service';

@Component({
  selector: 'app-rating-modal',
  templateUrl: './rating-modal.component.html',
  styleUrls: ['./rating-modal.component.scss']
})
export class RatingModalComponent implements OnInit {
  @Input() book: Book={};
  title = "rating.title";
  info="rating.info"
  labelKeyword = "Słowa kluczowe"
  labelSubject = "Tematyka"
  initialRating = 0;
  ratingForm:FormGroup= this.init();
  
  constructor(public activeModal: NgbActiveModal, public booksService:BooksService, private fb:FormBuilder) { }
  ngOnInit(): void {
    console.log(this.ratingForm)
  }

  onCancel = () => {
    console.log(this.ratingForm.value)
    //this.activeModal.dismiss('Cross click');
  }

  onConfirm = () => {
    this.activeModal.dismiss('Cross click');
  }

  init(){
    return this.fb.group({
      rating:this.initialRating
    })
  }

}
