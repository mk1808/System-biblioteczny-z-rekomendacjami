import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { BehaviorSubject } from 'rxjs';
import { AppUser, Book, ChangeProposal, KeyWord, Opinion } from 'src/app/core/services/rest/api/api';
import { BooksService } from 'src/app/core/services/rest/books.service';
import { DictionaryService } from 'src/app/core/services/rest/dictionary.service';
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
  labelKeyword = "Słowakluczowe"
  labelSubject = "Tematyka"
  initialRating = 0;
  commentInput="comment";
  comment="comment"
  ratingForm:FormGroup= this.init();
  keywords: BehaviorSubject<KeyWord[]> = new BehaviorSubject<KeyWord[]>([]);
  //keyword="keywords"
  
  constructor(public activeModal: NgbActiveModal, public booksService:BooksService, private fb:FormBuilder, private userService: UsersService, private dictionaryService: DictionaryService) { }
  ngOnInit(): void {
    console.log(this.ratingForm)
    this.updateDictionaryValues()
    this.whoAmI();
  }

  onCancel = () => {
    console.log(this.ratingForm.value)
    this.activeModal.dismiss('Cross click');
  }

  onConfirm = () => {
    this.save()
    
  }

  init(){
    return this.fb.group({
      rating:this.initialRating,
      comment:"",
      keyword:[]
    })
  }

  save(){
    let opinion:Opinion = this.ratingForm.value;
    opinion.bookId = this.book.id;
    opinion.userId = this.user.id;

    this.booksService.createOpinion(opinion).subscribe(response=>{
      console.log(response)
      this.activeModal.close(response);

    })
    debugger;
    let proposals: ChangeProposal[] = [];
    this.ratingForm.value.keyword.forEach((singleKeyword: any) => {
      proposals.push(this.createChangeProposal(singleKeyword));
    })

    this.booksService.createChangeProposal(proposals).subscribe(response => {
      console.log(response)
    })

  }

  whoAmI() {
    this.userService.whoAmI().subscribe(userPrincipal => {
      this.user.id = userPrincipal.principal.id;
    })
  }
  updateDictionaryValues(){
    this.dictionaryService.getKeyWords();
    this.keywords = this.dictionaryService.keywords;
  }

  createChangeProposal(keywordValue:any){
    let changeProposal:ChangeProposal = {};

    changeProposal.bookId = this.book.id;
    changeProposal.userId = this.user.id;
    changeProposal.type="keyword";
    changeProposal.value = keywordValue;
    return changeProposal;
  }

}
