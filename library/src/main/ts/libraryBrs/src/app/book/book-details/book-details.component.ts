import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { switchMap } from 'rxjs';

@Component({
  selector: 'app-book-details',
  templateUrl: './book-details.component.html',
  styleUrls: ['./book-details.component.scss']
})
export class BookDetailsComponent implements OnInit {
  id: any = 0;
  ids: any;
  title="bookDetails";
  rulesHeight="rulesHeight";
  constructor(private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.getBookId();
  }

  getBookId() {
    this.id = this.route.snapshot.paramMap.get('id');
    console.log(this.id)
  }

}
