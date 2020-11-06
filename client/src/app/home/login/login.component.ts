import { AuthService } from './../../core/auth/auth.service';
import { Router, ActivatedRoute } from '@angular/router';

import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  fromUrl: string;
  loginForm: FormGroup;
  @ViewChild('userNameInput', { static: true }) userNameInput: ElementRef<HTMLInputElement>;

  constructor(  private formBuilder: FormBuilder,
                private authService: AuthService,
                private router: Router,
                private activatedRoute: ActivatedRoute ) { }

  ngOnInit() {
    this.activatedRoute.queryParams.subscribe( params => {
      this.fromUrl = params['fromUrl'];
    })
    this.loginForm = this.formBuilder.group({
      userName: ['vilnei', Validators.required],
      password: [ '123456', Validators.required]
    });
  }

  login(){
    const userName = this.loginForm.get('userName').value;
    const password = this.loginForm.get('password').value;
    this.authService.autenticate( userName, password)
      .subscribe(
        () => {
                if(this.fromUrl){  
                   this.router.navigateByUrl( this.fromUrl );
                } else {
                   this.router.navigate(['/home']);
                }
              } ,
        err => {  console.log('** Algo deu errado !');
                //  this.router.navigate(['/home']);
                  this.loginForm.reset();
                  this.userNameInput.nativeElement.focus()
                }
      );
  }
}
