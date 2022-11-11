import { Component, OnInit, Input } from '@angular/core';
import { Subscription } from 'rxjs';
import { Guia } from 'src/app/guias/guia';
import { ActivatedRoute, Router, Params } from '@angular/router';
import { GuiasService } from 'src/app/guias/guias.service';
import { map } from 'rxjs/internal/operators/map';
import { Location } from '@angular/common';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { AuthService } from 'src/app/core/auth/auth.service';
import { User } from 'src/app/core/user/user';
import * as jtw_decode from 'jwt-decode';
import { switchMap } from 'rxjs/operators';

@Component({
  selector: 'app-guia-detail',
  templateUrl: './guia-detail.component.html',
  styleUrls: ['./guia-detail.component.css']
})
export class GuiaDetailComponent implements OnInit {
  inscricao: Subscription;
  guia: any;
  //  guia: any = new Guia();
  loginForm: FormGroup;
   habilitarAutenticacao: boolean = false;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private guiasService: GuiasService,
    private location: Location,
    private formBuilder: FormBuilder,
    private authService: AuthService
  ) { }

  ngOnInit() {
    this.loginForm = this.formBuilder.group({
      userName: ['', Validators.required],
      password: [ '', Validators.required]
    });

     this.inscricao = this.route.params
     .pipe(
           switchMap(
             (params: Params) =>
             this.guiasService.getGuia( params['id'] )
            ),
           map( resp => {
             this.guia = resp } )
         )
     .subscribe();
/** **/
    this.inscricao = this.route.params.subscribe(
      ( params: any ) => {
          let hsbilitar: boolean = params['autenticar'] == "true" ? true : false ;
          this.habilitarAutenticacao =  hsbilitar;
          this.guiasService.getGuia( params['id'] )
            .subscribe( guia => this.guia = guia  );
          });

    }

    login(){
      const userName = this.loginForm.get('userName').value;
      const password = this.loginForm.get('password').value;
      this.authService.autenticate( userName, password )
        .subscribe(
          ( resp ) => {
                  const authToken = resp.body.tipo+' '+resp.body.token ;
                  this.atualizarGuia( authToken );
                  this.voltar();
                } ,
          err => {  console.log('** Algo deu errado !');
                  //  this.router.navigate(['/home']);
                    this.loginForm.reset();
                  //  this.userNameInput.nativeElement.focus()
                  }
        );
    }

    atualizarGuia( token ) {
      const user = jtw_decode(token) as User ;
      let userName = user.name;
      let matricula = user.matricula;
      this.guia.matriculaRecebiemnto = matricula;
      this.guia.dataRecebimento = new Date();
      this.guiasService.updateGuia( this.guia )
            .subscribe( guia => this.guia = guia );
    }

    voltar(): void{
      this.location.back();
    }

    ngOnDestroy(){
      this.inscricao.unsubscribe();
    }

  }
