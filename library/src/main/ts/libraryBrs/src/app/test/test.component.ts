import { Component, OnInit } from '@angular/core';
import { UsersService } from '../core/services/rest/users.service';

@Component({
  selector: 'app-test',
  templateUrl: './test.component.html',
  styleUrls: ['./test.component.scss']
})
export class TestComponent implements OnInit {

  constructor(private userService: UsersService) { }

  ngOnInit(): void {
    this.userService.getById("120dd42e-11c4-4a82-b4d8-333a0a841cf9").subscribe(response=>{
      console.log(response);
    })
  }

}
