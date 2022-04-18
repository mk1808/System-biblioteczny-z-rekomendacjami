import { AfterViewInit, Component, OnInit } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { HeaderService } from 'src/app/core/services/header.service';
import { UsersService } from 'src/app/core/services/rest/users.service';
declare let $: any;
@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit, AfterViewInit {
  logged: BehaviorSubject<Boolean>;
  navButtons:any;
  constructor(private usersService: UsersService, private headerService:HeaderService) {
    this.logged = this.usersService.logged;
    this.logged.subscribe(x => {//debugger;
      console.log(x)
    })
  }

  ngOnInit(): void {

    this.navButtons = this.headerService.navButtons;

  }
  ngAfterViewInit() {

    //let elements = this.element.nativeElement.querySelectorAll('.ui.dropdown');
    //    setTimeout(this.loadJQuery);
    this.loadJQuery();
  }


  loadJQuery() {
    /*debugger
    let select:any = $('.search.dropdown')[0];
     select.dropdown();
     debugger*/
    $('.menu.dmenu').dropdown();
  }

  logout(){
    debugger;
    this.usersService.logout();
  }
}
