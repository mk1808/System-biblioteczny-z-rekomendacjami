import { Component, OnInit } from '@angular/core';
import { AppUser } from 'src/app/core/services/rest/api/api';
import { RecommendationsService } from 'src/app/core/services/rest/recommendations.service';
import { UsersService } from 'src/app/core/services/rest/users.service';

@Component({
  selector: 'app-recommendation-list',
  templateUrl: './recommendation-list.component.html',
  styleUrls: ['./recommendation-list.component.scss']
})
export class RecommendationListComponent implements OnInit {
  title="recommendations";
  rulesHeight="rulesHeight";
  textOpen="abc"
  textDropdown="options"
  currentRate = 6;
  no=0;
  book={id:0, photo:'https://picsum.photos/200/300', title:'Lorem ipsum dolor', author:'Dolores Gibson'}
  books=[this.getBook(),this.getBook(),this.getBook(),this.getBook(),this.getBook(),this.getBook(),this.getBook() ];
  recommendations=[]
  user:AppUser={};
  constructor(private recommendationsService:RecommendationsService, private userService:UsersService) { }

  ngOnInit(): void {
    this.whoAmI();
    this.getRecommendations();
  }
  test() {
    console.log('tets')
  }

  getBook(){
    this.no++;
    this.book.id=this.no;
    return this.book;
  }

  whoAmI() {
    this.userService.whoAmI().subscribe(userPrincipal => {
      this.user.id = userPrincipal.principal.id;
      this.getRecommendations();
    })
  }

  getRecommendations(){
    if(this.user.id){
      this.recommendationsService.getByUserId(this.user.id).subscribe(resp=>{
        console.log(resp)
      })
    }
      
  }
}
