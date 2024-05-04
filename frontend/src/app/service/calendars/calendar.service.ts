import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, of, take } from 'rxjs';
import { Calendar } from '../../models/calendar';

@Injectable({
  providedIn: 'root'
})
export class CalendarService {
  private path = '/api/calendars';

  constructor(private http: HttpClient) {}
  
  getCalendars(): Observable<any> {
    return this.http.get<Calendar[]>(this.path);
  }

  getCalendar(uuid: string): Observable<any> {
    return this.http.get<Calendar>(this.path + "/" + uuid)
  }

  deleteCalendar(uuid: string): Observable<any> {
    return this.http.delete<Calendar>(this.path + "/" + uuid)
  }

  updateCalendar(uuid: string, calendar: Calendar): Observable<any> {
    return this.http.put<Calendar>(this.path + "/" + uuid, calendar)
  }

  createCalendar(calendar: Calendar): Observable<any> {
    return this.http.post<Calendar>(this.path, calendar)
  }
  
}
