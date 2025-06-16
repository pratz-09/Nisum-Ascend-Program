import React from 'react';
import { Bell, Circle } from 'lucide-react';
import '../styles/Notifications.css';

interface Notification {
  id: number;
  message: string;
  date: string;
  timestamp: string;
  isRead: boolean;
}

interface NotificationsProps {
  notifications: Notification[];
}

const Notifications: React.FC<NotificationsProps> = ({ notifications }) => {
  return (
    <div className="notifications-container">
      <div className="notifications-header">
        <Bell className="notifications-icon" size={20} />
        <h3>Notifications</h3>
      </div>
      
      <div className="notifications-list">
        {notifications.map((notification) => (
          <div 
            key={notification.id} 
            className={`notification-item ${notification.isRead ? 'read' : 'unread'}`}
          >
            <div className="notification-content">
              <div className="notification-status">
                <Circle 
                  size={8} 
                  className={notification.isRead ? 'read-indicator' : 'unread-indicator'} 
                />
                <span className="status-text">
                  {notification.isRead ? 'Read' : 'Unread'}
                </span>
              </div>
              
              <p className="notification-message">{notification.message}</p>
              
              <div className="notification-meta">
                <span className="notification-date">{notification.date}</span>
                <span className="notification-timestamp">{notification.timestamp}</span>
              </div>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
};

export default Notifications;