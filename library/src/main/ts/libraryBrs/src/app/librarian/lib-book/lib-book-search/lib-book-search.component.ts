import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { BehaviorSubject } from 'rxjs';
import { Author, Book, BookAvailability, BookFIlter, Genre, Publisher } from 'src/app/core/services/rest/api/api';
import { BooksService } from 'src/app/core/services/rest/books.service';
import { DictionaryService } from 'src/app/core/services/rest/dictionary.service';

@Component({
  selector: 'app-lib-book-search',
  templateUrl: './lib-book-search.component.html',
  styleUrls: ['./lib-book-search.component.scss']
})
export class LibBookSearchComponent implements OnInit {

  title: any = "booksSearch";
  iconClass = "search"
  text = "search"
  label = "Tytuł"
  btnClass = "full"
  bookTitle = "title";
  bookCopyNo = "bookCopyNo";
  ISBN = "ISBN"
  no = 0;
  book = { id: 0, photo: 'https://picsum.photos/200/300', title: 'Lorem ipsum dolor', author: 'Dolores Gibson' }
  books = [this.getBook(), this.getBook(), this.getBook(), this.getBook(), this.getBook(), this.getBook(), this.getBook()];
  shouldDisplayAlert = false;
  submitBtnType = "submit";
  searchForm: FormGroup = new FormGroup({});
  genres: BehaviorSubject<Genre[]> = new BehaviorSubject<Genre[]>([]);
  authors: BehaviorSubject<Author[]> = new BehaviorSubject<Author[]>([]);
  publishers: BehaviorSubject<Publisher[]> = new BehaviorSubject<Publisher[]>([]);
  booksFiltered: BehaviorSubject<Book[]> = new BehaviorSubject<Book[]>([]);
  availabilities: BookAvailability[]=[];

  constructor(private booksService: BooksService,
    private dictionaryService: DictionaryService) { }

  ngOnInit(): void {
    this.initForm();
    this.updateDictionariesValues()
    this.updateBook();
  }

  updateDictionariesValues() {
    this.dictionaryService.getAuthors();
    this.dictionaryService.getGenres();
    this.dictionaryService.getPublishers();
    this.authors = this.dictionaryService.authors;
    this.genres = this.dictionaryService.genres;
    this.publishers = this.dictionaryService.publishers;
    console.log(this.authors)

    console.log(this.publishers)
    console.log(this.genres)

  }

  updateBook(){
    this.booksService.getFiltered(0,0,{})
    this.booksFiltered=this.booksService.searchedBooks;
    this.booksFiltered.subscribe(resp=>{
      this.prepareAvailabilityInfo();
    })
    debugger;
    
  }

  initForm() {
    this.searchForm = new FormGroup({
      title: new FormControl(''),
      ISBN: new FormControl(''),
      authorId: new FormControl(''),
      genreId: new FormControl(''),
      publisherId: new FormControl(''),
      bookCopyNo: new FormControl('')
    });
  }

  getBook() {
    this.no++;
    this.book.id = this.no;
    return this.book;
  }

  close(event:any) {
    this.shouldDisplayAlert = false;
  }

  search(){
    this.shouldDisplayAlert = true;
  }

  onSubmit(form: any) {
    console.log(this.searchForm.value)
    let bookFilter: BookFIlter = this.searchForm.value;
    debugger;
    bookFilter.authorId = this.replaceDefaultValue(bookFilter.authorId);
    bookFilter.genreId = this.replaceDefaultValue(bookFilter.genreId);
    bookFilter.publisherId = this.replaceDefaultValue(bookFilter.publisherId);
   
    this.booksService.getFiltered(0, 0, bookFilter);
    this.booksFiltered=this.booksService.searchedBooks;

    this.prepareAvailabilityInfo();


  }

  getAuthorDisplayedValue(option:any){
   
    return `${option.name} ${option.surname}`
  
}

replaceDefaultValue(value:string|undefined){
  return value=="0"?"":value;
}

prepareAvailabilityInfo(){
  let books:string[]= []
  this.booksFiltered.value.forEach(book=>books.push(book.id?book.id:""));
  this.getBooksAvailability(books);
}

getBooksAvailability(ids:string[]) {
  this.booksService.getAvailabilityByBookIds(ids).subscribe(resp=>{
    this.availabilities = resp.content
    console.log(resp)
  })
}


}
