import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MaterialNovoComponent } from './material-novo.component';

describe('MaterialNovoComponent', () => {
  let component: MaterialNovoComponent;
  let fixture: ComponentFixture<MaterialNovoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MaterialNovoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MaterialNovoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
