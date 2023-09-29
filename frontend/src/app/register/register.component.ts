import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { User } from '../user';
import { LtsService } from '../lts.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {
  msg:String | undefined;
  constructor(private ltsService:LtsService,private router: Router){}
  registerUser(user: User) {
    this.ltsService.registerUser(user).subscribe(
      data => {
        localStorage.setItem('loginUser',JSON.stringify(data))
        if(user.role === "employee"){
          this.router.navigate(["/dashboard"]);
        }else{
          this.router.navigate(["/new-leave"]);
        }
        
      },
      error => {
        this.msg = "User with " + user.email + " already exists.";
      }
    );
  }

  haveAccount(){
    this.router.navigate(["/login"]);
  }
}
