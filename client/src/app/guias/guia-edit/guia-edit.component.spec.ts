import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GuiaEditComponent } from './guia-edit.component';

describe('GuiaEditComponent', () => {
  let component: GuiaEditComponent;
  let fixture: ComponentFixture<GuiaEditComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GuiaEditComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GuiaEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
