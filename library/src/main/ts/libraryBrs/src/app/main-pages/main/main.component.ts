import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.scss']
})
export class MainComponent implements OnInit {

  constructor(private route: ActivatedRoute, private router: Router) { }

  ngOnInit(): void {
  }

  list(){
    this.router.navigate(['/books/search']);
  }

  contact(){
    this.router.navigate(['/home/contact']);
  }

  borrowingRules(){
    this.router.navigate(['/user/myAccount']);
  }

  login(){
    this.router.navigate(['/auth/login']);
  }

}
