import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-rules',
  templateUrl: './rules.component.html',
  styleUrls: ['./rules.component.scss']
})
export class RulesComponent implements OnInit {
  title="borrowingRules";
  rulesHeight="rulesHeight";
  constructor() { }

  ngOnInit(): void {
  }

}
