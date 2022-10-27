import { PendenciasModule } from './pendencias/pendencias.module';
import { MovimentacoesModule } from './movimentacoes/movimentacoes.module';
import { AppGlobalErrorhandler } from './core/auth/AppGlobalErrorhandler';
import { CoreModule } from './core/core.module';
import { HomeModule } from './home/home.module';
import { MateriaisModule } from './materiais/materiais.module';
import { EstoquesModule } from './estoques/estoques.module';
//import { AppBootstrapModule } from './app-bootstrap/app-bootstrap.module';
import { MateriaisService } from './materiais/materiais.service';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule, ErrorHandler } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
//import { HeaderComponent } from './header/header.component';
//import { HomeComponent } from './home/home.component';
import {  HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { GuiasModule } from 'src/app/guias/guias.module';
import { TransportasdorasModule } from 'src/app/transportadoras/transportadoras.module';
import { RelatoriosModule } from 'src/app/relatorios/relatorios.module';
//import { TypeaheadModule } from 'ngx-bootstrap/typeahead';

@NgModule({
  declarations: [
    AppComponent,
   // HeaderComponent
  //  HomeComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
  //  AppBootstrapModule,
    FormsModule,
    EstoquesModule,
    MateriaisModule,
    HomeModule,
    CoreModule,
    MovimentacoesModule,
    GuiasModule,
    TransportasdorasModule,
   // TypeaheadModule.forRoot()
    RelatoriosModule,
    PendenciasModule
  ],
  providers: [{provide: ErrorHandler, useClass: AppGlobalErrorhandler}],
  bootstrap: [AppComponent]
})
export class AppModule { }
