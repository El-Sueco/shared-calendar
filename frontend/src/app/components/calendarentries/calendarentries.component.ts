import { AfterContentChecked, AfterViewInit, Component, Input, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { CalendarEntriesDataSource } from '../../service/datasource/calendarentries/calendarentrydatasource.service';
import { CalendarEntryService } from '../../service/calendarntries/calendarentry.service';

@Component({
  selector: 'app-calendarentries',
  templateUrl: './calendarentries.component.html',
  styleUrl: './calendarentries.component.scss'
})
export class CalendarentriesComponent implements OnInit {

  displayedColumns: string[] = ['name', 'startDateTime', 'endDateTime', 'actions'];
  uuid?: string | undefined;
  dataSource!: CalendarEntriesDataSource;
  
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;

  constructor(
    private route: ActivatedRoute,
    private calendarEntryService: CalendarEntryService
  ) { }
  
  ngOnInit(): void {
    this.dataSource = new CalendarEntriesDataSource(this.calendarEntryService);
    this.route.queryParams.subscribe(params => {
      this.uuid = params['uuid'];
      if(this.uuid !== undefined){
        this.loadCalendarEntries(this.uuid);
      }   
    })
  }

  loadCalendarEntries(calendarUuid: string) {
    this.dataSource.loadCalendarEntries(calendarUuid);
  }
}
