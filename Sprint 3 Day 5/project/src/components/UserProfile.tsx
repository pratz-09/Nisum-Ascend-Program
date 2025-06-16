import React from 'react';
import { User, Mail, FileText } from 'lucide-react';
import '../styles/UserProfile.css';

interface UserProfileProps {
  name?: string;
  email?: string;
  avatarUrl?: string;
  bio?: string;
}

const UserProfile: React.FC<UserProfileProps> = ({
  name = "John Doe",
  email = "john@example.com",
  avatarUrl,
  bio = "No bio available."
}) => {
  return (
    <div className="user-profile-card">
      <div className="user-avatar">
        {avatarUrl ? (
          <img src={avatarUrl} alt={name} className="avatar-image" />
        ) : (
          <div className="avatar-placeholder">
            <User size={40} />
          </div>
        )}
      </div>
      
      <div className="user-info">
        <h3 className="user-name">{name}</h3>
        
        <div className="user-detail">
          <Mail size={16} />
          <span>{email}</span>
        </div>
        
        <div className="user-bio">
          <FileText size={16} />
          <p>{bio}</p>
        </div>
      </div>
    </div>
  );
};

export default UserProfile;