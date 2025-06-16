import React from 'react';
import UserProfile from './UserProfile';
import Notifications from './Notifications';

const Dashboard: React.FC = () => {
  const sampleNotifications = [
    {
      id: 1,
      message: "Your profile has been updated successfully.",
      date: "2024-01-15",
      timestamp: "10:30 AM",
      isRead: false
    },
    {
      id: 2,
      message: "New comment on your post about React components.",
      date: "2024-01-14",
      timestamp: "2:45 PM",
      isRead: true
    },
    {
      id: 3,
      message: "Weekly report is ready for review.",
      date: "2024-01-14",
      timestamp: "9:00 AM",
      isRead: false
    }
  ];

  return (
    <div className="dashboard-container">
      <h2 className="text-2xl font-bold text-gray-800 mb-6">Dashboard</h2>
      
      <div className="grid grid-cols-1 lg:grid-cols-2 gap-6">
        <UserProfile 
          name="Alice Johnson"
          email="alice.johnson@example.com"
          bio="Full-stack developer with 5 years of experience in React and Node.js. Passionate about creating beautiful and functional user interfaces."
        />
        <Notifications notifications={sampleNotifications} />
      </div>
    </div>
  );
};

export default Dashboard;