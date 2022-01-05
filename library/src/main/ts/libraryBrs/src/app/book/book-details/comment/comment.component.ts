import { Component, Input, OnInit } from '@angular/core';
import { I18nService } from 'src/app/core/services/i18n.service';
import { BookAvailabilityComponent } from '../book-availability/book-availability.component';

@Component({
  selector: 'app-comment',
  templateUrl: './comment.component.html',
  styleUrls: ['./comment.component.scss']
})
export class CommentComponent implements OnInit {
  @Input() comment:any;
  constructor(private i18nService: I18nService) { }

  ngOnInit(): void {
  }

}