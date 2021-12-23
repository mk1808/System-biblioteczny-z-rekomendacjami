import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-two-columns-content',
  templateUrl: './two-columns-content.component.html',
  styleUrls: ['./two-columns-content.component.scss']
})
export class TwoColumnsContentComponent implements OnInit {
  @Input() leftClass="";
  @Input() rightClass="";
  constructor() { }

  ngOnInit(): void {
  }

}
