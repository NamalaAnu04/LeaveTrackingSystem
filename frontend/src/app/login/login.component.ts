import { Component } from '@angular/core';
import { Route, Router } from '@angular/router';
import { NgForm } from '@angular/forms';
import { User } from '../user';
import { LtsService } from '../lts.service';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  msg!:String;
  constructor(private ltsService:LtsService,private router: Router){}
  loginUser(user: User) {
    this.ltsService.loginUser(user).subscribe(
      (data: any) => {localStorage.setItem('loginUser',JSON.stringify(data))
        if (data && data.role === 'employee') {
          this.router.navigate(['/dashboard']);
        } else {
          this.router.navigate(['/new-leave']);
        }
      },
      (error) => (this.msg = 'Invalid Credentials')
    );
  }
  noAccount(){
    this.router.navigate(["/signup"]);
  }

}
