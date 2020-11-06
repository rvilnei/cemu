

import { NgScrollbarModule } from 'ngx-scrollbar';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { TypeaheadModule } from 'ngx-bootstrap/typeahead';
import { BsDropdownModule } from 'ngx-bootstrap/dropdown';
import { TooltipModule } from 'ngx-bootstrap/tooltip';
import { ModalModule } from 'ngx-bootstrap/modal';
//import { BsModalRef, BsModalService } from 'ngx-bootstrap/modal';
import { SortableModule } from 'ngx-bootstrap/sortable';
import { TabsModule } from 'ngx-bootstrap/tabs';
import { TabsetComponent } from 'ngx-bootstrap'
import { PaginationModule } from 'ngx-bootstrap/pagination';

import {NgxPaginationModule} from 'ngx-pagination';

@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    BsDropdownModule.forRoot(),
    TooltipModule.forRoot(),
    ModalModule.forRoot(),
    SortableModule.forRoot(),
    TypeaheadModule.forRoot(),
    TabsModule.forRoot(),
    PaginationModule.forRoot(),
    NgxPaginationModule,
    NgScrollbarModule
  ],
 // providers: [ BsModalService ],
  exports: [ 
    BsDropdownModule, TooltipModule, ModalModule, SortableModule,
    TabsModule, PaginationModule, NgxPaginationModule, NgScrollbarModule
   ]
})
export class AppBootstrapModule { }
